package com.hejia.dataAnalysis.module.search.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hejia.dataAnalysis.module.common.exception.ServiceException;
import com.hejia.dataAnalysis.module.search.dao.SearchIndexDao;
import com.hejia.dataAnalysis.module.search.domain.SearchIndex;
import com.hejia.dataAnalysis.module.search.service.SearchIndexService;

/**
 * @Description: 
 * @author: chenyongqiang
 * @Date: 2017年7月29日
 * @version: 1.0
 */
@Service("searchIndexService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
public class SearchIndexServiceImpl implements SearchIndexService<SearchIndex> {
	
	@Autowired
	private SearchIndexDao dao;
	
	public SearchIndex add(SearchIndex domain) throws ServiceException {
		return dao.save(domain);
	}

	public SearchIndex modify(SearchIndex domain) throws ServiceException {
		return dao.save(domain);
	}

	public SearchIndex delete(SearchIndex domain) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public Page<SearchIndex> find(SearchIndex domain, Pageable p) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<SearchIndex> findByTypes(Collection<Integer> types) throws ServiceException {
		return dao.findByTypeIn(types);
	}

}
