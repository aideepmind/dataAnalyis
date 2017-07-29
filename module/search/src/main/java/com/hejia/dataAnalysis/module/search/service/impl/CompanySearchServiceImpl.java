package com.hejia.dataAnalysis.module.search.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.NumericDocValuesField;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
//import org.apache.lucene.document.IntField;
//import org.apache.lucene.document.StringField;
//import org.apache.lucene.document.TextField;
//import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.queryparser.flexible.standard.config.StandardQueryConfigHandler.Operator;
import org.apache.lucene.sandbox.queries.DuplicateFilter;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.NumericRangeQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.MMapDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import com.hejia.dataAnalysis.module.search.common.ModuleConfig;
import com.hejia.dataAnalysis.module.search.common.lucene.LuceneUtils;
import com.hejia.dataAnalysis.module.search.common.lucene.analyer.CustomIKAnalyer;
import com.hejia.dataAnalysis.module.search.common.lucene.analyer.CustomStandardAnalyzer;
import com.hejia.dataAnalysis.module.recruitment.dao.lagou.CompanyCoreInfoDao;
import com.hejia.dataAnalysis.module.recruitment.dao.lagou.PositionDao;
import com.hejia.dataAnalysis.module.search.dao.SearchIndexDao;
import com.hejia.dataAnalysis.module.recruitment.dao.lagou.impl.CompanyJoinDaoImpl;
import com.hejia.dataAnalysis.module.recruitment.dao.lagou.impl.PositionDaoImpl;
import com.hejia.dataAnalysis.module.recruitment.domain.lagou.CompanyCoreInfo;
import com.hejia.dataAnalysis.module.recruitment.domain.lagou.Position;
import com.hejia.dataAnalysis.module.search.domain.SearchIndex;
import com.hejia.dataAnalysis.module.search.service.CompanySearchService;
import com.hejia.dataAnalysis.module.recruitment.service.lagou.PositionService;
import com.hejia.dataAnalysis.module.search.service.SearchIndexService;
import com.hejia.dataAnalysis.module.search.service.SearchService;
import com.hejia.dataAnalysis.module.account.domain.Option;
import com.hejia.dataAnalysis.module.account.service.OptionService;
import com.hejia.dataAnalysis.module.common.Constant;
import com.hejia.dataAnalysis.module.common.domain.BaseDomain;
import com.hejia.dataAnalysis.module.common.domain.RequestArg;
import com.hejia.dataAnalysis.module.common.exception.ServiceException;
import com.hejia.dataAnalysis.module.common.utils.DateUtils;
import com.mongodb.BasicDBObject;


/**
 * @Description: 
 * @author: chenyongqiang
 * @Date: 2015年10月9日
 * @version: 1.0
 */
@Service("companySearchService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
public class CompanySearchServiceImpl implements CompanySearchService {
	
	private static final Logger log = Logger.getLogger(CompanySearchServiceImpl.class);
	
	public static final String DELIMITER = " ";
	
	@Autowired
	private PositionDao positionDao;
	@Autowired
	private PositionDaoImpl positionDaoImpl;
	@Autowired
	private SearchIndexService<SearchIndex> searchIndexService;
	@Autowired
	private CompanyCoreInfoDao companyCoreInfoDao;
	@Autowired
	private CompanyJoinDaoImpl companyJoinDaoImpll;
	@Autowired
	private OptionService<Option> optionService;
	
	private static Directory boxIndex;//下拉框索引
	private static Directory posListIndex;//列表索引
	private static Directory compListIndex;//列表索引
	
	/*
	 * 分词器
	 */
	private static Analyzer ikAnalyzer = null;//true表智能分词，例如分“高级工程师”，true只有一个分词，而false时，会分词成高级、工程师、高级工程师等等
	
//	private static Analyzer standardAnalyzer = new CustomStandardAnalyzer();
	
	/*
	 * 设计思路：
	 * kw过来，分词
	 * 1.用于城市、学历要求等等显示在页面上
	 * 2.找出权重比，用于优先级
	 * 两部分索引
	 * 1.下拉框的索引（内容、类型、数量），例如：java高级工程师、职位、400+
	 * 2.数据查询的索引（内容、职位id），例如：北京java工程师1~5k本科全职移动互联网阿里巴巴
	 */
	public Page<Map> searchPosition(RequestArg ra, Pageable p) throws ServiceException {
		IndexReader lr = null;
		try {
			String kw = ra.getStringAndNotNull("kw"); // 关键字
			if (StringUtils.isBlank(kw)) {
				return new PageImpl<Map>(new ArrayList<Map>(0), p, 0);
			}
			String fs = ra.getStringAndNotNull("fs"); // 通过搜索框from search
			String si = ra.getStringAndNotNull("si"); // 通过建议suggest input
			String ct = ra.getStringAndNotNull("ct"); // 工作城市
			String ex = ra.getStringAndNotNull("ex"); // 工作经验
			String de = ra.getStringAndNotNull("de"); // 学历要求
			String tr = ra.getStringAndNotNull("tr"); // 所在行业
			String sa = ra.getStringAndNotNull("sa"); // 月薪
			String ty = ra.getStringAndNotNull("ty"); // 工作性质
			String or = ra.getString("or");//排序
			//组合查询条件
//			kw = kw + DELIMITER + ct + DELIMITER + ex + DELIMITER + de + DELIMITER + tr + DELIMITER + sa + DELIMITER + ty;
			lr = LuceneUtils.getIndexReader(posListIndex);
			IndexSearcher ls = LuceneUtils.getIndexSearcher(lr);
			BooleanQuery bq = new BooleanQuery();
			QueryParser lqp = new QueryParser("content", ikAnalyzer);
			lqp.setDefaultOperator(QueryParser.AND_OPERATOR);
			Query lq = lqp.parse(LuceneUtils.escapeCharacter(kw));
			bq.add(lq, BooleanClause.Occur.MUST);
			if (StringUtils.isNotBlank(de)) {
				TermQuery tq = new TermQuery(new Term("degree", de));
				bq.add(tq, BooleanClause.Occur.MUST);
			}
			if (StringUtils.isNotBlank(ex)) {
				lqp = new QueryParser("experience", ikAnalyzer);
				lqp.setDefaultOperator(QueryParser.AND_OPERATOR);
				lq = lqp.parse(LuceneUtils.escapeCharacter(ex));
				bq.add(lq, BooleanClause.Occur.MUST);
			}
			if (StringUtils.isNotBlank(ct)) {
				TermQuery tq = new TermQuery(new Term("city", ct));
				bq.add(tq, BooleanClause.Occur.MUST);
			}
			if (StringUtils.isNotBlank(tr)) {
				lqp = new QueryParser("trade", ikAnalyzer);
				lqp.setDefaultOperator(QueryParser.AND_OPERATOR);
				lq = lqp.parse(LuceneUtils.escapeCharacter(tr));
				bq.add(lq, BooleanClause.Occur.MUST);
			}
			if (StringUtils.isNotBlank(ty)) {
				TermQuery tq = new TermQuery(new Term("workType", ty));
				bq.add(tq, BooleanClause.Occur.MUST);
			}
			if (StringUtils.isNotBlank(sa)) {
				TermQuery tq = new TermQuery(new Term("salary", sa));
				bq.add(tq, BooleanClause.Occur.MUST);
			}
			Page page = null;
			//显示职位优先
			if ("最新".equals(or)) {
				Sort sort = new Sort(new SortField("publishDate", SortField.Type.LONG, true));//应该按发布时间排序
				page = LuceneUtils.query(ls, bq, p, sort);
			} else {
				page = LuceneUtils.query(ls, bq, p);
			}
			List<Document> dList = page.getContent();
			if (dList.size() > 0) {
				List<Map> mList = new ArrayList<Map>(dList.size());
				for (int i = 0; i < dList.size(); i++) {
					Document d = dList.get(i);
					Map<String, Object> m = new HashMap<String, Object>(20);
					m.put("positionId", d.get("id"));
					m.put("compId", d.get("unionId"));
					m.put("status", d.get("status"));
					m.put("positionName", d.get("positionName"));
					m.put("companyName", d.get("companyName"));
					m.put("city", d.get("city"));
					m.put("workType", d.get("workType"));
					//有可能是年薪结算
					IndexableField salary = d.getField("salary");
					if (salary != null && !"".equals(salary.stringValue())) {
						m.put("salary", salary.stringValue());
					} else {
						m.put("yearSalary", d.get("yearSalary"));
					}
					m.put("experience", d.get("experience"));
					m.put("degree", d.get("degree"));
					m.put("publishDate", d.get("pDate"));
					m.put("temptation", d.get("temptation"));
					mList.add(m);
					// 找公司信息
					
				}
				return new PageImpl<Map>(mList, p, page.getTotalElements());
				// 返回相似度最高
//				List<Document> bdList = LuceneUtils.query(bs, bq, 1);
				
			}
			// 判断是精确查询还是模糊查询
//			boolean isFuzzy = ra.getBoolean("isFuzzy");
//			if (true) {
//				// 判断是关键字的权重比大概属于的范围，并以此得出优先级
//				// 模糊查询
//				p = pDaoImpl.search(ra, pr);
//				List<Position> pList = p.getContent();
//				// 查询公司资料
//			}
		} catch (Exception e) {
			log.debug("搜索职位时出错，错误是：", e);
			throw new ServiceException("获取关键字时出错，错误是：", e);
		} finally {
			LuceneUtils.closeIndexReader(lr);
		}
		return new PageImpl<Map>(new ArrayList<Map>(0), p, 0);
	}
	
	public Page<Map> searchCompany(RequestArg ra, Pageable p) throws ServiceException {
		IndexReader r = null;
		try {
			String kw = ra.getStringAndNotNull("kw");//关键字
			if (StringUtils.isBlank(kw)) {
				return new PageImpl<Map>(new ArrayList<Map>(0), p, 0);
			}
			
			/*
			//去重
			DuplicateFilter filter = new DuplicateFilter("cid", DuplicateFilter.KeepMode.KM_USE_FIRST_OCCURRENCE, DuplicateFilter.ProcessingMode.PM_FAST_INVALIDATION);
			data = LuceneUtils.query(s, bq, filter, pr);
			List<Document> dList = (List<Document>) data.get("data");*/
			
			r = LuceneUtils.getIndexReader(compListIndex);
			IndexSearcher s = LuceneUtils.getIndexSearcher(r);
			BooleanQuery bq = new BooleanQuery();
			//kw
			QueryParser qp = new QueryParser("content", ikAnalyzer);
			qp.setDefaultOperator(QueryParser.AND_OPERATOR);
			Query q = qp.parse(LuceneUtils.escapeCharacter(kw));
			bq.add(q, BooleanClause.Occur.MUST);
			
			Page page  = LuceneUtils.query(s, bq, p);
			List<Document> dList = page.getContent();
			if (dList.size() > 0) {
				List<Map> mList = new ArrayList<Map>(dList.size());
				for (int i = 0; i < dList.size(); i++) {
					Document d = dList.get(i);
					int id = Integer.parseInt(d.get("id"));
					//找公司信息
					Map<String, Object> m = new HashMap<String, Object>(10);
					m.put("compId", id);
					m.put("logo", d.get("logo"));
					m.put("companyUrl", d.get("companyUrl"));
					m.put("trade", d.get("trade"));
					m.put("city", d.get("city"));
					m.put("companyIntroduce", d.get("companyIntroduce"));
					m.put("companyName", d.get("companyName"));
					m.put("companyShortName", d.get("companyShortName"));
					mList.add(m);
				}
				return new PageImpl<Map>(mList, p, page.getTotalElements());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e);
		} finally {
			LuceneUtils.closeIndexReader(r);
		}
		return new PageImpl<Map>(new ArrayList<Map>(0), p, 0);
	}
	
	/**
	 * 获取关键字
	 */
	public Page<Map> findKeyword(String kw, Pageable p) throws ServiceException {
		IndexReader reader = null;
		try {
			if (StringUtils.isNotBlank(kw)) {
				reader = LuceneUtils.getIndexReader(boxIndex);
				if (reader != null) {
					IndexSearcher searcher = LuceneUtils.getIndexSearcher(reader);
					if (searcher != null) {
						QueryParser qp = new QueryParser("content", ikAnalyzer);
						qp.setDefaultOperator(QueryParser.AND_OPERATOR);
						Query q = qp.parse(LuceneUtils.escapeCharacter(kw));
						Page<Document> page = LuceneUtils.query(searcher, q, p);
						List<Document> dList = page.getContent();
						if (dList.size() > 0) {
							List<Map> mList = new ArrayList<Map>();
							for (int i = 0; i < dList.size(); i++) {
								Document doc = dList.get(i);
								Map<String, String> m = new HashMap<String, String>(3);
								m.put("name", doc.getField("name").stringValue());
								m.put("type", doc.getField("type").stringValue());
								m.put("count", doc.getField("count").stringValue());
								mList.add(m);
							}
							return new PageImpl<Map>(mList, p, page.getTotalElements());
						}
					}
				}
			}
		} catch (Exception e) {
			log.debug("获取关键字时出错，错误是：", e);
			throw new ServiceException("获取关键字时出错，错误是：", e);
		} finally {
			LuceneUtils.closeIndexReader(reader);
		}
		return new PageImpl<Map>(new ArrayList<Map>(0), p, 0);
	}

	/**
	 * 初始化
	 */
	public void init() throws ServiceException {
		ikAnalyzer = new CustomIKAnalyer(false);
		String path = null;
		if (Constant.runEnv == 3) {
			path = ModuleConfig.get("search_company_index_path_win");
		} else {
			path = ModuleConfig.get("search_company_index_path_linux");
		}
		if (StringUtils.isBlank(path)) {
			throw new ServiceException("搜索配置文件不存在，请在web项目的根目录下创建web-common.properties的文件并且配置search_company_index_path_win和search_company_index_path_linux");
		}
		boxIndex = LuceneUtils.createDirectory(LuceneUtils.DIRECTORY_TYPE_MMAP, path + "_box");
		posListIndex = LuceneUtils.createDirectory(LuceneUtils.DIRECTORY_TYPE_MMAP, path + "_pos_list");
		compListIndex = LuceneUtils.createDirectory(LuceneUtils.DIRECTORY_TYPE_MMAP, path + "_comp_list");
		
//		initPosListIndex();
		initCompListIndex();
//		initBoxIndex();
		
		log.info("--------------------------初始化搜索引擎成功！----------------------------------");
	}
	
	/**
	 * @Definition: 初始化下拉框索引，先建立列表索引，总数根据列表索引得到
	 * @author: chenyongqiang
	 * @Date: 2015年10月11日
	 */
	private void initBoxIndex() {
		IndexWriter writer = null;
		IndexReader reader = null;
		try {
			writer = LuceneUtils.getIndexWriter(boxIndex, ikAnalyzer, OpenMode.CREATE);
			// 查找索引表，把相应职位和公司的索引写入索引中
			
			// 加上得分（score）和排序（sort）和收集器（collector，主要过滤重复的数据，例如搜索出来凯普斯和北京凯普斯咨询服务有限公司）
			
			List<Integer> types = new ArrayList<Integer>();
			types.add(SearchIndex.TYPE_POSITION);
			types.add(SearchIndex.TYPE_COMPANY);
			List<SearchIndex> siList = searchIndexService.findByTypes(types);
			
			reader = LuceneUtils.getIndexReader(posListIndex);
			IndexSearcher searcher = LuceneUtils.getIndexSearcher(reader);
			QueryParser qp = new QueryParser("content", ikAnalyzer);
			qp.setDefaultOperator(QueryParser.AND_OPERATOR);
			
			Directory ramDir = LuceneUtils.createDirectory(LuceneUtils.DIRECTORY_TYPE_RAM, null);
			IndexWriter rWriter = LuceneUtils.getIndexWriter(ramDir, ikAnalyzer, OpenMode.CREATE);
			Document d;
			for (int i = 0; i < siList.size(); i++) {
				SearchIndex si = siList.get(i);
				d = new Document();
				//content、type、count、name、id
				Field f = new TextField("content", si.getContent(), Field.Store.YES);
//				if (si.getContent().contains("MySQL")) {
//					f.setBoost(10);
//				}
				d.add(f);// 索引但存储
				d.add(new IntField("type", si.getType(), Field.Store.YES));//索引并存储
				d.add(new TextField("name", si.getName(), Field.Store.YES));//索引并存储
				d.add(new IntField("id", si.getSiId(), Field.Store.YES));//索引并存储
				
				//qp.escape(si.getName())
				//LuceneUtils.escapeCharacter(si.getName())
				int count = 0;
				if (si.getType() == SearchIndex.TYPE_COMPANY) {
					// 查找职位的总数
//					Integer cid = Integer.parseInt(si.getOther());
//					NumericRangeQuery<Integer> nrq = NumericRangeQuery.newIntRange("compId", cid, cid, true, true);
//					count = LuceneUtils.searchTotalRecord(searcher, nrq);
					d.add(new StringField("cid", si.getOther(), Field.Store.YES)); // 索引并存储
				}
				
				Query q = qp.parse(LuceneUtils.escapeCharacter(si.getName()));
				count = LuceneUtils.searchTotalRecord(searcher, q);
				d.add(new IntField("count", count, Field.Store.YES)); // 索引并存储
				rWriter.addDocument(d);
				if (i > 0 && i % 500 == 0) { // 随便设个值
					rWriter.close();
					writer.addIndexes(ramDir);
					rWriter = LuceneUtils.getIndexWriter(ramDir, ikAnalyzer, OpenMode.CREATE);
				}
			}
			if (siList.size() == 1 || (siList.size() -1) % 500 != 0) {
				rWriter.close();
				writer.addIndexes(ramDir);
				writer.commit();
			}
			ramDir.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			LuceneUtils.closeIndexReader(reader);
			LuceneUtils.closeIndexWriter(writer);
		}
	}
	
	/**
	 * @Definition: 初始化职位列表索引
	 * @author: chenyongqiang
	 * @Date: 2015年10月11日
	 */
	private void initPosListIndex() {
		IndexWriter writer = null;
		try {
			writer = LuceneUtils.getIndexWriter(posListIndex, ikAnalyzer, OpenMode.CREATE);
			// 查找职位相关所有信息，组成索引内容
			List<Position> pList = positionDao.findAll();
			if (pList.size() > 0) {
				RAMDirectory ramDir = new RAMDirectory();
				IndexWriter rWriter = LuceneUtils.getIndexWriter(ramDir, ikAnalyzer, OpenMode.CREATE);
				for (int i = 0; i < pList.size(); i++) {
					Position p = pList.get(i);
					rWriter.addDocument(createLagouPosListDocument(p));
					if (i > 0 && i % 500 == 0) { // 随便设个值
						rWriter.close();
						writer.addIndexes(ramDir);
						rWriter = LuceneUtils.getIndexWriter(ramDir, ikAnalyzer, OpenMode.CREATE);
					}
				}
				if (pList.size() == 1 || (pList.size() -1) % 500 != 0) {
					rWriter.close();
					writer.addIndexes(ramDir);
					writer.commit();
				}
				ramDir.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			LuceneUtils.closeIndexWriter(writer);
		}
	}

	/**
	 * @Definition: 初始化公司列表索引
	 * @author: chenyongqiang
	 * @Date: 2015年10月11日
	 */
	private void initCompListIndex() {
		IndexWriter writer = null;
		IndexReader reader = null;
		try {
			writer = LuceneUtils.getIndexWriter(compListIndex, ikAnalyzer, OpenMode.CREATE);
			
			int pages = 0;
			while (pages < 10) {
				PageRequest pr = new PageRequest(pages, 500);
				List<CompanyCoreInfo> companyCoreInfos = companyCoreInfoDao.findAll(pr).getContent();
				if (!companyCoreInfos.isEmpty()) {
					List<String> idList = new ArrayList<String>();
					for (int i = 0; i < companyCoreInfos.size(); i++) {
						idList.add(companyCoreInfos.get(i).get_id());
					}
					List<BasicDBObject> basicDBObjects = companyJoinDaoImpll.findByAllCollection(idList);
					RAMDirectory ramDir = new RAMDirectory();
					IndexWriter rWriter = LuceneUtils.getIndexWriter(ramDir, ikAnalyzer, OpenMode.CREATE);
					for (int i = 0; i < basicDBObjects.size(); i++) {
						BasicDBObject basicDBObject = basicDBObjects.get(i);
						Document d = this.createLagouCompListDocument(basicDBObject);
						rWriter.addDocument(d);
						if (i > 0 && i % 500 == 0) {//随便设个值
							rWriter.close();
							writer.addIndexes(ramDir);
							rWriter = LuceneUtils.getIndexWriter(ramDir, ikAnalyzer, OpenMode.CREATE);
						}
					}
					if (basicDBObjects.size() == 1 || (basicDBObjects.size() -1) % 500 != 0) {
						rWriter.close();
						writer.addIndexes(ramDir);
						writer.commit();
					}
					ramDir.close();
				} else {
					break;
				}
				pages++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			LuceneUtils.closeIndexReader(reader);
			LuceneUtils.closeIndexWriter(writer);
		}
	}
	
	/**
	 * @Definition: 获取职位列表索引
	 * @author: chenyongqiang
	 * @Date: 2015年10月15日
	 * @param p
	 * @return
	 */
	private Document createLagouPosListDocument(Position p) {
		// 找出职位相关的信息，公司、城市等
		// TextField和StringField的区别：StringField不分词，TextField分词
		Document d = new Document();
		d.add(new StringField("id", p.getPositionId().toString(), Field.Store.YES)); // 索引并存储
		//d.add(new NumericDocValuesField("idSort", p.getPositionId().intValue())); // 索引并存储
		d.add(new IntField("compId", p.getCompanyId(), Field.Store.YES)); // 索引并存储
		d.add(new StoredField("positionName", p.getPositionName())); // 只存储
		d.add(new StoredField("companyShortName", p.getCompanyShortName())); // 只存储
		d.add(new StoredField("companyFullName", p.getCompanyFullName())); // 只存储
		d.add(new StoredField("pDate", p.getCreateTime())); // 只存储
		d.add(new StringField("city", p.getCity(), Field.Store.YES)); // 索引并存储
		//经验
		d.add(new TextField("experience", p.getWorkYear(), Field.Store.YES)); // 索引并存储
		//学历
		d.add(new StringField("degree", p.getEducation(), Field.Store.YES)); // 索引并存储
		//行业
		d.add(new TextField("trade", p.getIndustryField(), Field.Store.YES)); // 索引并存储
		//工作性质
		d.add(new StringField("workType", p.getJobNature(), Field.Store.YES)); // 索引并存储
		d.add(new TextField("salary", p.getSalary(), Field.Store.YES)); // 索引并存储
		
		
		StringBuilder b = new StringBuilder();
		b.append(p.getPositionName()).append(DELIMITER);
		b.append(p.getCity()).append(DELIMITER);
		b.append(p.getEducation()).append(DELIMITER);
		b.append(p.getWorkYear()).append(DELIMITER);
		b.append(p.getJobNature()).append(DELIMITER);
		b.append(p.getSalary()).append(DELIMITER);
		b.append(p.getCompanyFullName()).append(DELIMITER);
		b.append(p.getCompanyShortName()).append(DELIMITER);
		b.append(p.getDistrict()).append(DELIMITER);
		b.append(p.getFinanceStage()).append(DELIMITER);
		b.append(p.getIndustryField()).append(DELIMITER);
		b.append(p.getSecondType()).append(DELIMITER);
		b.append(p.getCompanySize()).append(DELIMITER);
		b.append(p.getPositionAdvantage());
		
		d.add(new TextField("content", b.toString(), Field.Store.NO));//索引并存储，主要下面还要用到，并且数据量不是很大
		
		return d;
	}
	
	/**
	 * @Definition: 获取公司列表索引
	 * @author: chenyongqiang
	 * @Date: 2017年7月29日
	 * @param basicDBObject
	 * @return
	 */
	private Document createLagouCompListDocument(BasicDBObject basicDBObject) {
		// 取出core信息
		String _id = basicDBObject.getString("_id");
		String companyName = basicDBObject.getString("companyName");
		String companyShortName = basicDBObject.getString("companyShortName");
		String companyIntroduce = basicDBObject.getString("companyIntroduce");
		String logo = basicDBObject.getString("logo");
		String companyUrl = basicDBObject.getString("companyUrl");
		// 取出base信息
		String companySize = basicDBObject.getString("companySize");
		String industryField = basicDBObject.getString("industryField");
		String city = basicDBObject.getString("city");
		String financeStage = basicDBObject.getString("financeStage");
		// 取出address信息
		String province = basicDBObject.getString("province");
		String district = basicDBObject.getString("district");
		// 取出leader信息
		String leaderName = basicDBObject.getString("name");
		String leaderPosition = basicDBObject.getString("position");
		// 取出product信息
		String product = basicDBObject.getString("product");
		String productprofile = basicDBObject.getString("productprofile");
		// 取出introduction信息
		String companyProfile = basicDBObject.getString("companyProfile");
		
		Document d = new Document();
		StringBuilder b = new StringBuilder(); // 暂时只要名称作为公司的搜索条件，后期可以加上例如500强之类的关键字作为条件
		d.add(new StringField("id", _id, Field.Store.YES)); // 索引并存储
//		d.add(new NumericDocValuesField("idSort", a.getAccId())); // 排序		
		d.add(new StoredField("companyName", companyName)); // 只存储	
		d.add(new StoredField("companyShortName", companyShortName)); // 只存储
		d.add(new StoredField("logo", StringUtils.isBlank(logo) ? "" : logo)); // 只存储
		d.add(new StoredField("companyUrl", companyUrl)); // 只存储
		d.add(new StoredField("trade", industryField)); // 只存储
		d.add(new StoredField("city", city)); // 只存储
		d.add(new StoredField("companyIntroduce", companyIntroduce)); // 只存储
		
		b.append(companyName).append(DELIMITER);
		b.append(companyShortName).append(DELIMITER);
		b.append(companyIntroduce).append(DELIMITER);
		b.append(companySize).append(DELIMITER);
		b.append(industryField).append(DELIMITER);
		b.append(city).append(DELIMITER);
		b.append(financeStage).append(DELIMITER);
		b.append(province).append(DELIMITER);
		b.append(district).append(DELIMITER);
		b.append(leaderName).append(DELIMITER);
		b.append(leaderPosition).append(DELIMITER);
		b.append(product).append(DELIMITER);
		b.append(productprofile).append(DELIMITER);
		b.append(companyProfile);

		d.add(new TextField("content", b.toString(), Field.Store.NO)); // 索引并存储，主要下面还要用到，并且数据量不是很大
		
		return d;
	}
	
	/**
	 * @Definition: 两种途径增加下拉框索引，一种是人工增加，另一种是机器学习
	 * @author: chenyongqiang
	 * @Date: 2015年10月11日
	 */
	public void addBoxIndex() {
		
	}
	
	
	public String analyzer(String kw) throws ServiceException {
		if (StringUtils.isBlank(kw)) {
			return "";
		}
		IKSegmenter ik = new IKSegmenter(new StringReader(kw), true);
        try {
        	Lexeme l = null; 
			while((l = ik.next()) != null)
				if (isCity(l.getLexemeText())) {
					return l.getLexemeText();
				}
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return "";
	}
	
	/**
	 * @Definition: 是否为城市
	 * @author: chenyongqiang
	 * @Date: 2016年4月27日
	 * @param data
	 * @return
	 */
	private boolean isCity(String data) {
		List<Option> oList = optionService.findByTypeAndName(Option.TYPE_CITY_BY_LETTER, data);
		return !oList.isEmpty();
	}
}
