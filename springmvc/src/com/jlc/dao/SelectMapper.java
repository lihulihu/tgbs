package com.jlc.dao;

import com.jlc.bean.Select;

public interface SelectMapper {
    int deleteByPrimaryKey(Long selectId);

    int insert(Select record);

    int insertSelective(Select record);

    Select selectByPrimaryKey(Long selectId);

    int updateByPrimaryKeySelective(Select record);

    int updateByPrimaryKey(Select record);
}