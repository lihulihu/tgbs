package com.jlc.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jlc.bean.tree;
import com.jlc.dao.TreeMapper;
import com.jlc.service.TreeService;
@Service
public class TreeServiceImpl implements TreeService{
	@Autowired
	public TreeMapper treeMapper;
	public List<tree> selectByParam(Map map){
		return treeMapper.selectByParam(map);
	}
}
