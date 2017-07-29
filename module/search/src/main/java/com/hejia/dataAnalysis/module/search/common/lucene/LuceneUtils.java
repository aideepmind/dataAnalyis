package com.hejia.dataAnalysis.module.search.common.lucene;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.text.Highlighter;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Scorer;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.FileSwitchDirectory;
import org.apache.lucene.store.MMapDirectory;
import org.apache.lucene.store.NIOFSDirectory;
import org.apache.lucene.store.NRTCachingDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * @Description: lucene工具类
 * @author: chenyongqiang
 * @Date: 2015年10月13日
 * @version: 1.0
 */
public class LuceneUtils {
	
	private static final Logger log = Logger.getLogger(LuceneUtils.class);
	
	/**
	 * 目录类型
	 */
	public static final int DIRECTORY_TYPE_RAM = 1;
	public static final int DIRECTORY_TYPE_FS = 2;
	public static final int DIRECTORY_TYPE_MMAP = 3;
	public static final int DIRECTORY_TYPE_NRTC = 4;
	public static final int DIRECTORY_TYPE_CF = 5;
	public static final int DIRECTORY_TYPE_NIOFS = 6;
	public static final int DIRECTORY_TYPE_SFS = 7;
	public static final int DIRECTORY_TYPE_FSW = 8;
	public static final int MAX_PAGE_COUNT = 100;//暂时不能超过100页
	
	
	/**
	 * @Definition: 创建索引
	 * @author: chenyongqiang
	 * @Date: 2015年10月13日
	 * @param type
	 * @param uri
	 * @return
	 */
	public static Directory createDirectory(int type, String uri) {
		Directory d = null;
		try {
			if (type == DIRECTORY_TYPE_RAM) {
				d = new RAMDirectory();
			} else if (type == DIRECTORY_TYPE_FS) {
				d = FSDirectory.open(Paths.get(uri));
			} else if (type == DIRECTORY_TYPE_MMAP) {
				d = MMapDirectory.open(Paths.get(uri));
			} else if (type == DIRECTORY_TYPE_NRTC) {
//				Directory fsDir = FSDirectory.open(Paths.get(uri));
//				d = new NRTCachingDirectory(fsDir, 5.0, 60.0);
			} else if (type == DIRECTORY_TYPE_CF) {
//				d = CompoundFileDirectory
			} else if (type == DIRECTORY_TYPE_NIOFS) {
//				d = NIOFSDirectory.open(Paths.get(uri));
			} else if (type == DIRECTORY_TYPE_SFS) {
//				d = new SimpleFSDirectory(Paths.get(uri));
			} else if (type == DIRECTORY_TYPE_FSW) {//后期用
//				d = new FileSwitchDirectory(new HashSet<String>(), MMapDirectory.open(Paths.get(uri)), MMapDirectory.open(Paths.get(uri)), true);
			}
			//isLocked方法内部会试图去获取Lock,如果获取到Lock，会关闭它，否则return false表示索引目录没有被锁
			IndexWriter.isLocked(d);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return d;
	}
	
	/**
	 * @Definition: 关闭索引
	 * @author: chenyongqiang
	 * @Date: 2015年10月13日
	 * @param d
	 */
	public static void closeDirectory(Directory d) {
		if (d != null) {
			try {
				d.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @Definition: 获取写入器
	 * @author: chenyongqiang
	 * @Date: 2015年10月13日
	 * @param d
	 * @param a
	 * @return
	 */
	public static IndexWriter getIndexWriter(Directory d, Analyzer a, OpenMode om) {
		IndexWriter w = null;
		try {
			IndexWriterConfig iwc = new IndexWriterConfig(a);
			iwc.setOpenMode(om);//删除同一文件下的索引，新建新的索引，还有两个属性：APPEND（追加）、CREATE_OR_APPEND、CREATE
			w = new IndexWriter(d, iwc);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return w;
	}
	
	/**
	 * @Definition: 获取阅读器
	 * @author: chenyongqiang
	 * @Date: 2015年10月13日
	 * @param d
	 * @return
	 */
	public static IndexReader getIndexReader(Directory d) {
		IndexReader r = null;
		try {
			r = DirectoryReader.open(d);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return r;
	}
	
	/**
	 * @Definition: 获取搜索器
	 * @author: chenyongqiang
	 * @Date: 2015年10月13日
	 * @param r
	 * @return
	 */
	public static IndexSearcher getIndexSearcher(IndexReader r) {
		IndexSearcher s = new IndexSearcher(r);
		return s;
	}
	
	/**
	 * @Definition: 获取查询分析器
	 * @author: chenyongqiang
	 * @Date: 2015年10月13日
	 * @param f
	 * @param a
	 * @return
	 */
	public static QueryParser getQueryParser(String f, Analyzer a) {
		return new QueryParser(f, a);
	}
	
	/**
	 * @Definition: 关闭写入器
	 * @author: chenyongqiang
	 * @Date: 2015年10月13日
	 * @param w
	 */
	public static void closeIndexWriter(IndexWriter w) {
		if (w != null) {
			try {
				w.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @Definition: 关闭阅读器
	 * @author: chenyongqiang
	 * @Date: 2015年10月13日
	 * @param r
	 */
	public static void closeIndexReader(IndexReader r) {
		if (r != null) {
			try {
				r.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @Definition: 更新索引文档
	 * @author: chenyongqiang
	 * @Date: 2015年10月13日
	 * @param w
	 * @param term
	 * @param doc
	 */
	public static void updateIndex(IndexWriter w, Term term, Document doc) {
		try {
			w.updateDocument(term, doc);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Definition: 更新索引文档
	 * @author: chenyongqiang
	 * @Date: 2015年10月13日
	 * @param w
	 * @param f
	 * @param v
	 * @param doc
	 */
	public static void updateIndex(IndexWriter w, String f, String v, Document doc) {
		updateIndex(w, new Term(f, v), doc);
	}
	
	/**
	 * @Definition: 添加索引文档
	 * @author: chenyongqiang
	 * @Date: 2015年10月13日
	 * @param w
	 * @param doc
	 */
	public static void addIndex(IndexWriter w, Document doc) {
		updateIndex(w, null, doc);
	}
	
	/**
	 * @Definition: 索引文档查询
	 * @author: chenyongqiang
	 * @Date: 2015年10月13日
	 * @param s
	 * @param query
	 * @return
	 */
	public static List<Document> query(IndexSearcher s, Query q) {
		return query(s, q, Integer.MAX_VALUE);
	}
	
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2015年10月13日
	 * @param s
	 * @param query
	 * @param num
	 * @return
	 */
	public static List<Document> query(IndexSearcher s, Query q, int num) {
		List<Document> dList = null;
		try {
			TopDocs topDocs = s.search(q, num);
			ScoreDoc[] scores = topDocs.scoreDocs;
			int length = scores.length;
			if (length == 0) {
				return Collections.emptyList();
			}
			dList = new ArrayList<Document>();
			for (int i = 0; i < length; i++) {
				Document doc = s.doc(scores[i].doc);
				dList.add(doc);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dList;
	}
	
	/**
	 * @Definition: 分页查询
	 * @author: chenyongqiang
	 * @Date: 2015年10月23日
	 * @param s
	 * @param q
	 * @param p
	 * @return
	 * @throws IOException 
	 */
	public static Page<Document> query(IndexSearcher s, Query q, Pageable p) throws IOException {
		return query(s, q, p, null);
	}
	
	/**
	 * @Definition: 分页并且排序
	 * @author: chenyongqiang
	 * @Date: 2015年10月23日
	 * @param s
	 * @param q
	 * @param p
	 * @param sort
	 * @return
	 * @throws IOException 
	 */
	public static Page<Document> query(IndexSearcher s, Query q, Pageable p, Sort sort) throws IOException {
		return query(s, q, null, p, sort);
	}
	
	/**
	 * @Definition: 分页查询
	 * @author: chenyongqiang
	 * @Date: 2016年4月25日
	 * @param s
	 * @param q
	 * @param filter
	 * @param p
	 * @return
	 * @throws IOException 
	 */
	public static Page<Document> query(IndexSearcher s, Query q, Filter filter, Pageable p) throws IOException {
		return query(s, q, filter, p, null);
	}
	
	/**
	 * @Definition: 分页并且排序
	 * @author: chenyongqiang
	 * @Date: 2016年4月25日
	 * @param s
	 * @param q
	 * @param filter
	 * @param p
	 * @param sort
	 * @return
	 * @throws IOException 
	 */
	public static Page<Document> query(IndexSearcher s, Query q, Filter filter, Pageable p, Sort sort) throws IOException {
		Page<Document> page = null;
		try {
			// 不能超过规定的页数
			if (p.getPageNumber() + 1 > MAX_PAGE_COUNT) {
				return new PageImpl<Document>(new ArrayList<Document>(0), p, MAX_PAGE_COUNT * p.getPageSize());
			}
			TopDocs topDocs = null;
			if (filter != null && sort != null) {
				topDocs = s.search(q, filter, (p.getPageNumber() + 1) * p.getPageSize(), sort);
			} else if (filter != null) {
				topDocs = s.search(q, filter, (p.getPageNumber() + 1) * p.getPageSize());
			} else if (sort != null) {
				topDocs = s.search(q, (p.getPageNumber() + 1) * p.getPageSize(), sort);
			} else {
				topDocs = s.search(q, (p.getPageNumber() + 1) * p.getPageSize());
			}
			ScoreDoc[] scores = topDocs.scoreDocs;
			int length = scores.length;
			int start = p.getPageNumber() * p.getPageSize();
			if (length == 0 || start >= length) {
				return new PageImpl<Document>(new ArrayList<Document>(0), p, length);
			}
			List<Document> dList = new ArrayList<Document>();
			for (int i = start; i < length; i++) {
				Document doc = s.doc(scores[i].doc);
				dList.add(doc);
			}
			page = new PageImpl<Document>(dList, p, length);
		} catch (IOException e) {
			log.debug("分页查询时出错，错误是：", e);
			throw e;
		}
		return page;
	}
	
	/**
	 * @Definition: 过滤
	 * @author: chenyongqiang
	 * @Date: 2016年4月25日
	 * @param s
	 * @param q
	 * @param filter
	 * @param num
	 * @return
	 */
	public static List<Document> query(IndexSearcher s, Query q, Filter filter, int num) {
		List<Document> dList = null;
		try {
			TopDocs topDocs = s.search(q, filter, num);
			ScoreDoc[] scores = topDocs.scoreDocs;
			int length = scores.length;
			if (length == 0) {
				return Collections.emptyList();
			}
			dList = new ArrayList<Document>();
			for (int i = 0; i < length; i++) {
				Document doc = s.doc(scores[i].doc);
				dList.add(doc);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dList;
	}
	
	/**
	 * @Definition: 返回索引文档的总数
	 * @author: chenyongqiang
	 * @Date: 2015年10月13日
	 * @param r
	 * @return
	 */
	public static int getIndexTotalCount(IndexReader r) {
		return r.numDocs();
	}
	
	/**
	 * @Definition: 返回索引文档中最大文档ID
	 * @author: chenyongqiang
	 * @Date: 2015年10月13日
	 * @param r
	 * @return
	 */
	public static int getMaxDocId(IndexReader r) {
		return r.maxDoc();
	}
	
	/**
	 * @Definition: 返回已经删除尚未提交的文档总数
	 * @author: chenyongqiang
	 * @Date: 2015年10月13日
	 * @param r
	 * @return
	 */
	public static int getDeletedDocNum(IndexReader r) {
		return getMaxDocId(r) - getIndexTotalCount(r);
	}
	
	/**
	 * @Definition: 根据docId查询索引文档
	 * @author: chenyongqiang
	 * @Date: 2015年10月13日
	 * @param r
	 * @param docID
	 * @param fsToLoad
	 * @return
	 */
	public static Document findDocumentByDocId(IndexReader r, int docID, Set<String> fsToLoad) {
		Document doc = null;
		try {
			doc = r.document(docID, fsToLoad);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return doc;
	}
	
	/**
	 * @Definition: 根据docId查询索引文档
	 * @author: chenyongqiang
	 * @Date: 2015年10月13日
	 * @param r
	 * @param docID
	 * @return
	 */
	public static Document findDocumentByDocId(IndexReader r, int docID) {
		return findDocumentByDocId(r, docID, null);
	}
	
	/**
	 * @Definition: 获取符合条件的总记录数
	 * @author: chenyongqiang
	 * @Date: 2015年10月13日
	 * @param search
	 * @param query
	 * @return
	 */
	public static int searchTotalRecord(IndexSearcher s, Query q) {
		int count = 0;
		try {
			TopDocs topDocs = s.search(q, Integer.MAX_VALUE);
			count = topDocs.scoreDocs.length;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	/**
	 * @Definition: 获取符合条件的总记录数
	 * @author: chenyongqiang
	 * @Date: 2016年4月25日
	 * @param s
	 * @param q
	 * @param filter
	 * @return
	 */
	public static int searchTotalRecord(IndexSearcher s, Query q, Filter filter) {
		int count = 0;
		try {
			TopDocs topDocs = s.search(q, filter, Integer.MAX_VALUE);
			count = topDocs.scoreDocs.length;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	/**
	 * @Definition: 删除索引
	 * @author: chenyongqiang
	 * @Date: 2015年10月13日
	 * @param w
	 * @param f
	 * @param v
	 */
	public static void deleteIndex(IndexWriter w, String f, String v) {
		try {
			w.deleteDocuments(new Term[] {new Term(f, v)});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Definition: 删除索引
	 * @author: chenyongqiang
	 * @Date: 2015年10月13日
	 * @param w
	 * @param query
	 */
	public static void deleteIndex(IndexWriter w, Query q) {
		try {
			w.deleteDocuments(q);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Definition: 删除索引
	 * @author: chenyongqiang
	 * @Date: 2015年10月13日
	 * @param w
	 * @param terms
	 */
	public static void deleteIndexs(IndexWriter w, Term[] terms) {
		try {
			w.deleteDocuments(terms);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Definition: 批量删除索引
	 * @author: chenyongqiang
	 * @Date: 2015年10月13日
	 * @param w
	 * @param querys
	 */
	public static void deleteIndexs(IndexWriter w, Query[] querys) {
		try {
			w.deleteDocuments(querys);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Definition: 删除所有索引文档
	 * @author: chenyongqiang
	 * @Date: 2015年10月13日
	 * @param w
	 */
	public static void deleteAllIndex(IndexWriter w) {
		try {
			w.deleteAll();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Definition: 获取转义字符
	 * @author: chenyongqiang
	 * @Date: 2015年10月18日
	 * @param str
	 * @return
	 */
	public static String escapeCharacter(String str) {
		if (StringUtils.isBlank(str)) {
			return str;
		}
		char cs[] = str.toCharArray();
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < cs.length; i++) {
			char c = cs[i];
			if (c == '\\' || c == '+' || c == '-' || c == '!' || c == '(' || c == ')' || c == ':' || c == '^' || c == '[' || c == ']' || c == '\"' || c == '{' || c == '}' || c == '~' || c == '*' || c == '?' || c == '|' || c == '&' || c == '/') {
				b.append("\\");
			}
			b.append(c);
		}
		return b.toString();
	}
}
