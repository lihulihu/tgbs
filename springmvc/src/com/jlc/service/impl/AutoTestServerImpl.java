package com.jlc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.jlc.bean.Course;
import com.jlc.bean.PublicParam;
import com.jlc.bean.Select;
import com.jlc.bean.selectCourse;
import com.jlc.dao.PublicParamMapper;
import com.jlc.dao.ScoreMapper;
import com.jlc.dao.UserMapper;

public class AutoTestServerImpl {
	@Autowired
	private ScoreMapper scoreMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private PublicParamMapper publicParamMapper;
	public void selectCourseIsMust(){
		System.out.println("自动任务开始");
		Map<String,Object> pubMap = new HashMap<>();
		pubMap.put("publicCode", "schoolYear");
		pubMap.put("status", "1");
		PublicParam publicParam = null;
		try {
			publicParam = publicParamMapper.selectByParam(pubMap).get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<Map<String,Object>> list = userMapper.queryAllStudent();
		for(int i = 0; i < list.size() ; i++){
			Map<String,Object> paramMap = new HashMap<>(); 
			paramMap.put("grade", list.get(i).get("grade"));
			paramMap.put("studentId", list.get(i).get("id"));
			List<Map<String,Object>> courseList = scoreMapper.autoTest(paramMap);
			if(courseList.size() > 0){
				List<selectCourse> selectList = new ArrayList<>();
				for(int j = 0; j<courseList.size(); j++){
					selectCourse select = new selectCourse();
					Map<String,Object> map = courseList.get(j);
					select.setSelectCourseid(Long.parseLong(map.get("course_id").toString()));
					select.setSelectStudentid(Long.parseLong(list.get(i).get("id").toString()));
					select.setselectYear(publicParam.getPublicValueId().toString());
					select.setSelectTeacher((String)courseList.get(j).get("course_teacher"));
					selectList.add(select);
				}
				scoreMapper.batchAddSelectCourse(selectList);
				System.out.println("用户"+list.get(i).get("id").toString()+"新增条必须课程");
			}
		}
		System.out.println("自动任务结束");
	}
}
