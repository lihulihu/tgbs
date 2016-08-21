package com.jlc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jlc.bean.Select;
import com.jlc.commons.exception.ServiceException;
import com.jlc.dao.SelectMapper;
import com.jlc.service.SelectService;
@Service
public class SelectServiceImpl implements SelectService {
	
	 private static Logger LOGGER = LoggerFactory.getLogger(CourseServiceImpl.class);
	 @Autowired
	 private SelectMapper selectMapper;
	
	public int deleteByPrimaryKey(Long selectId){
	return 0;
	}

	public int insert(Select record){
        int insert = selectMapper.insert(record);
        if (insert != 1) {
            LOGGER.warn("插入课程失败，参数：{}", record.toString());
            throw new ServiceException("插入课程失败");
        }
        return insert;
	}

	public int insertSelective(Select record){
	return 0;
	}

	public Select selectByPrimaryKey(Long selectId){
	return null;
	}

	public int updateByPrimaryKeySelective(Select record){
	return 0;
	}

	public int updateByPrimaryKey(Select record){
	return 0;
	}
}
