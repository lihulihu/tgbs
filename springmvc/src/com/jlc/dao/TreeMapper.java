package com.jlc.dao;

import java.util.List;
import java.util.Map;

import com.jlc.bean.tree;


public interface TreeMapper {
    int deleteByPrimaryKey(Integer treeId);

    int insert(tree record);

    int insertSelective(tree record);

    tree selectByPrimaryKey(Integer treeId);

    int updateByPrimaryKeySelective(tree record);

    int updateByPrimaryKey(tree record);
    
    public List<tree> selectByParam(Map map);
}