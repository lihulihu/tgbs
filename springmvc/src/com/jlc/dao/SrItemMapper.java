package com.jlc.dao;

import java.util.List;
import java.util.Map;

import com.jlc.bean.SrItem;

public interface SrItemMapper {
    int deleteByPrimaryKey(Integer sSrId);

    int insert(SrItem record);

    int insertSelective(SrItem record);

    SrItem selectByPrimaryKey(Integer sSrId);

    int updateByPrimaryKeySelective(SrItem record);

    int updateByPrimaryKey(SrItem record);
    
    List<SrItem> selectByMap(Map<String,Object> map);
}