package com.hejia.dataAnalysis.module.recruitment.service;

import java.util.List;

import com.hejia.dataAnalysis.module.common.exception.ServiceException;
import com.hejia.dataAnalysis.module.common.service.BaseService;
import com.hejia.dataAnalysis.module.recruitment.domain.Position;

/**
 * @Description: 
 * @author: chenyongqiang
 * @Date: 2017年7月22日
 * @version: 1.0
 */
public interface PositionSerivce extends BaseService {
	
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2017年7月22日
	 * @param positionName
	 * @return
	 * @throws ServiceException
	 */
	public List<Position> findByPositionName(String positionName) throws ServiceException;
	
	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2017年7月24日
	 * @param position
	 * @return
	 * @throws ServiceException
	 */
	public List<Position> find(Position position) throws ServiceException;
	
}
