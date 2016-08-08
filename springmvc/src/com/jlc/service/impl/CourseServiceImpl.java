package com.jlc.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jlc.bean.Course;
import com.jlc.bean.Role;
import com.jlc.commons.exception.ServiceException;
import com.jlc.commons.result.UserVo;
import com.jlc.commons.utils.PageInfo;
import com.jlc.dao.CourseMapper;
import com.jlc.service.CourseService;
@Service
public class CourseServiceImpl implements CourseService {

	 private static Logger LOGGER = LoggerFactory.getLogger(CourseServiceImpl.class);
	 @Autowired
	 private CourseMapper courseMapper;
	 
	@Override
	public void addCourse(Course course) {
        int insert = courseMapper.insert(course);
        if (insert != 1) {
            LOGGER.warn("插入课程失败，参数：{}", course.toString());
            throw new ServiceException("插入课程失败");
        }
	}

	@Override
	public void deleteCourseById(Long id) {
        int update = courseMapper.deleteCourseById(id);
        if (update != 1) {
            LOGGER.warn("删除课程失败，id：{}", id);
            throw new ServiceException("删除课程失败");
        }
	}

	@Override
	public Course findCourseById(Long id) {
		return courseMapper.findCourseById(id);
	}

	@Override
	public void updateCourse(Course course) {
        int update = courseMapper.updateCourse(course);
        if (update != 1) {
            LOGGER.warn("更新失败，参数：{}", course.toString());
            throw new ServiceException("更新失败");
        }
	}

	@Override
    public void findDataGrid(PageInfo pageInfo) throws Exception{
        pageInfo.setRows(courseMapper.findCoursePageCondition(pageInfo));
        pageInfo.setTotal(courseMapper.findCoursePageCount(pageInfo));
    }

	@Override
    public Course findCourseByCourseName(String coursename) throws Exception{
        return courseMapper.findCourseByCourseName(coursename);
    }
	
}
