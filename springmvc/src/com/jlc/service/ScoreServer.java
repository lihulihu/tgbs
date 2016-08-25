package com.jlc.service;

import java.util.List;
import java.util.Map;

import com.jlc.bean.Score;

public interface ScoreServer {
	int deleteByPrimaryKey(Integer scoreId);

    int insert(Score record);

    int insertSelective(Score record);

    Score selectByPrimaryKey(Integer scoreId);

    int updateByPrimaryKeySelective(Score record);

    int updateByPrimaryKey(Score record);
    
    List<Map<String,Object>> selectScoreByStudentId(Map<String,Object> map);
}
