package com.jlc.service;

import java.util.List;
import java.util.Map;

import com.jlc.bean.Sr;

public interface SrService {
	public int deleteByPrimaryKey(Integer srId);

	public int insert(Sr record);

	public int insertSelective(Sr record);

	public Sr selectByPrimaryKey(Integer srId);

	public int updateByPrimaryKeySelective(Sr record);

	public int updateByPrimaryKey(Sr record);
	
	public List<Sr> selectList(Map<String,Object> map);
	
	public  Map<String,Object> selectByUserId(Map<String,Object> map);
}
