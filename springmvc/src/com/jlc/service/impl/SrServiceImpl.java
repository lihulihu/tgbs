package com.jlc.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jlc.bean.Sr;
import com.jlc.dao.SrMapper;
import com.jlc.service.SrService;
@Service
public class SrServiceImpl implements SrService{

	@Autowired
	private SrMapper srMapper;
	public int deleteByPrimaryKey(Integer srId){
		return srMapper.deleteByPrimaryKey(srId);
	}

	public int insert(Sr record){
		return srMapper.insert(record);
	}

	public int insertSelective(Sr record){
		return srMapper.insertSelective(record);
	}

	public Sr selectByPrimaryKey(Integer srId){
		return srMapper.selectByPrimaryKey(srId);
	}

	public int updateByPrimaryKeySelective(Sr record){
		return srMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(Sr record){
		return srMapper.updateByPrimaryKey(record);
	}
	public List<Sr> selectList(Map<String,Object> map){
		return srMapper.selectList(map);
	}
	
	public  Map<String,Object> selectByUserId(Map<String,Object> map){
		return srMapper.selectByUserId(map);
	}
}
