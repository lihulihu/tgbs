package com.jlc.service;

import com.jlc.bean.Course;
import com.jlc.bean.Role;
import com.jlc.commons.result.UserVo;
import com.jlc.commons.utils.PageInfo;

public interface CourseService {
	
    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    Course findCourseByCourseName(String coursename) throws Exception;
	
    /**
     * 添加用户
     *
     * @param course
     */
    void addCourse(Course course);
    
    /**
     * 根据id删除课程
     *
     * @param id
     */
    void deleteCourseById(Long id);
    
    /**
     * 根据id查询权限
     *
     * @param id
     * @return
     */
    Course findCourseById(Long id);
    
    /**
     * 修改课程
     *
     * @param Course
     * @return
     */
    void updateCourse(Course course);
    
    /**
     * 用户列表
     *
     * @param pageInfo
     */
    void findDataGrid(PageInfo pageInfo) throws Exception;
}
