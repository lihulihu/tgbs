package com.jlc.service;

import java.util.List;
import java.util.Map;

import com.jlc.bean.SrItem;

public interface SrItemService {
	public int deleteByPrimaryKey(Integer sSrId);

	public int insert(SrItem record);

	public int insertSelective(SrItem record);

	public SrItem selectByPrimaryKey(Integer sSrId);

	public int updateByPrimaryKeySelective(SrItem record);

	public int updateByPrimaryKey(SrItem record);
	
	public List<SrItem> selectByMap(Map<String,Object> map);

}
