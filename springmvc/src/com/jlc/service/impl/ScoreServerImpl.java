package com.jlc.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jlc.bean.Score;
import com.jlc.dao.ScoreMapper;
import com.jlc.service.ScoreServer;
@Service
public class ScoreServerImpl implements ScoreServer{
	@Autowired
	public ScoreMapper scoreMapper;
	public int deleteByPrimaryKey(Integer scoreId){
		return scoreMapper.deleteByPrimaryKey(scoreId);
	}

	public int insert(Score record){
		return scoreMapper.insert(record);
	}

	public int insertSelective(Score record){
		return scoreMapper.insertSelective(record);
	}

	public Score selectByPrimaryKey(Integer scoreId){
		return scoreMapper.selectByPrimaryKey(scoreId);
	}

	public int updateByPrimaryKeySelective(Score record){
		return scoreMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(Score record){
		return scoreMapper.updateByPrimaryKey(record);
	}
	
	public List<Map<String,Object>> selectScoreByStudentId(Map<String,Object> map){
		return scoreMapper.selectScoreByStudentId(map);
	}
}
