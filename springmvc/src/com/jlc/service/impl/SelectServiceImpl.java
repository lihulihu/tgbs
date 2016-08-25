package com.jlc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jlc.bean.selectCourse;
import com.jlc.commons.exception.ServiceException;
import com.jlc.dao.SelectCourseMapper;
import com.jlc.service.SelectService;
@Service
public class SelectServiceImpl implements SelectService {
	
	 private static Logger LOGGER = LoggerFactory.getLogger(CourseServiceImpl.class);
	 @Autowired
	 private SelectCourseMapper selectCourseMapper;
	
	public int deleteByPrimaryKey(Long selectId){
	return 0;
	}

	public int insert(selectCourse record){
        int insert = selectCourseMapper.insert(record);
        if (insert != 1) {
            LOGGER.warn("插入课程失败，参数：{}", record.toString());
            throw new ServiceException("插入课程失败");
        }
        return insert;
	}

	public int insertSelective(selectCourse record){
	return 0;
	}

	public selectCourse selectByPrimaryKey(Long selectId){
	return null;
	}

	public int updateByPrimaryKeySelective(selectCourse record){
	return 0;
	}

	public int updateByPrimaryKey(selectCourse record){
	return 0;
	}
}
