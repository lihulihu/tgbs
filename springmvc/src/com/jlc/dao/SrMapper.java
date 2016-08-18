package com.jlc.dao;


import java.util.List;
import java.util.Map;

import com.jlc.bean.Sr;

public interface SrMapper {
    int deleteByPrimaryKey(Integer srId);

    int insert(Sr record);

    int insertSelective(Sr record);

    Sr selectByPrimaryKey(Integer srId);

    int updateByPrimaryKeySelective(Sr record);

    int updateByPrimaryKey(Sr record);
    
    public List<Sr> selectList(Map<String,Object> map);
}