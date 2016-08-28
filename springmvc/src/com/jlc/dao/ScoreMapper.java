package com.jlc.dao;

import java.util.List;
import java.util.Map;

import com.jlc.bean.Course;
import com.jlc.bean.Score;
import com.jlc.bean.selectCourse;

public interface ScoreMapper {
    int deleteByPrimaryKey(Integer scoreId);

    int insert(Score record);

    int insertSelective(Score record);

    Score selectByPrimaryKey(Integer scoreId);

    int updateByPrimaryKeySelective(Score record);

    int updateByPrimaryKey(Score record);
    
    List<Map<String,Object>> selectScoreByStudentId(Map<String,Object> map);
    
    List<Map<String,Object>> selectScoreByStudentId1(Map<String,Object> map);
    
    List<Map<String,Object>> autoTest(Map<String,Object> map);
    
    int batchAddSelectCourse(List<selectCourse> list);
}