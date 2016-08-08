package com.jlc.dao;

import java.util.List;

import com.jlc.bean.Course;
import com.jlc.commons.result.UserVo;
import com.jlc.commons.utils.PageInfo;

public interface CourseMapper {
    /**
     * 添加课程
     *
     * @param role
     * @return
     */
    int insert(Course course);

    /**
     * 删除课程
     *
     * @param id
     * @return
     */
    int deleteCourseById(Long id);
    
    /**
     * 根据id查询课程
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
    int updateCourse(Course course);
    
    /**
     * 用户列表
     *
     * @param pageInfo
     * @return
     */
    List<Course> findCoursePageCondition(PageInfo pageInfo);
    
    /**
     * 统计用户
     *
     * @param pageInfo
     * @return
     */
    int findCoursePageCount(PageInfo pageInfo);
    
    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    Course findCourseByCourseName(String coursename);
}
