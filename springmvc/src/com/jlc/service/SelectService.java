package com.jlc.service;

import com.jlc.bean.selectCourse;

public interface SelectService {
  
    int deleteByPrimaryKey(Long selectId);

    int insert(selectCourse record);

    int insertSelective(selectCourse record);

    selectCourse selectByPrimaryKey(Long selectId);

    int updateByPrimaryKeySelective(selectCourse record);

    int updateByPrimaryKey(selectCourse record);
}
