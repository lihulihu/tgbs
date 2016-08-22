package com.jlc.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jlc.bean.SrItem;
import com.jlc.dao.SrItemMapper;
import com.jlc.service.SrItemService;
@Service
public class SrItemServiceImpl implements SrItemService{
	@Autowired
	private SrItemMapper srItemMapper;
	public int deleteByPrimaryKey(Integer sSrId){
		return srItemMapper.deleteByPrimaryKey(sSrId);
	}

	public int insert(SrItem record){
		return srItemMapper.insert(record);
	}

	public int insertSelective(SrItem record){
		return srItemMapper.insertSelective(record);
	}

	public SrItem selectByPrimaryKey(Integer sSrId){
		return srItemMapper.selectByPrimaryKey(sSrId);
	}

	public int updateByPrimaryKeySelective(SrItem record){
		return srItemMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(SrItem record){
		return srItemMapper.updateByPrimaryKey(record);
	}
	
	public List<SrItem> selectByMap(Map<String,Object> map){
		return srItemMapper.selectByMap(map);
	}
}
