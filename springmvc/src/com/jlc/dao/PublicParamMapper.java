package com.jlc.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jlc.bean.PublicParam;
import com.jlc.commons.utils.PageInfo;

public interface PublicParamMapper {
    int deleteByPrimaryKey(Integer publicId) throws Exception;

    int insert(PublicParam record) throws Exception;

    int insertSelective(PublicParam record) throws Exception;

    PublicParam selectByPrimaryKey(Integer publicId) throws Exception;

    int updateByPrimaryKeySelective(PublicParam record) throws Exception;

    int updateByPrimaryKey(PublicParam record) throws Exception;
    
    List<PublicParam> selectByParam(Map<String,Object> map) throws Exception;
    
    List<PublicParam> findParamPageCondition(PageInfo pageInfo);
    /**
     * 统计用户
     *
     * @param pageInfo
     * @return
     */
    int findParamPageCount(PageInfo pageInfo);
}