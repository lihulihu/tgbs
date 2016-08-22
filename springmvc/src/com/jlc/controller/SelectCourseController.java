/*package com.jlc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jlc.bean.Course;
import com.jlc.commons.base.BaseController;
import com.jlc.service.CourseService;

@Controller
@RequestMapping("/select")
public class SelectCourseController extends BaseController{
	
	@Autowired
    private SelectService scService;
	
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
    public String paramManage() {
        return "admin/selectCourse";
    }
	
    *//**
     * 插入选课记录
     *
     * @param userVo
     * @return
     *//*
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(Course course) {
        Course u = null;
		try {
			u = scService.findCourseByCourseName(course.getCourseName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if (u != null) {
            return renderError("课程已存在!");
        }
        course.setCourseName(course.getCourseName());
        course.setDescription(course.getDescription());
        course.setCapacity(course.getCapacity());
        course.setMajor(course.getMajor());
        course.setOrganizationId(course.getOrganizationId());
        course.setOrganizationName(course.getOrganizationName());
        try {
        	scService.addCourse(course);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return renderSuccess("添加成功");
    }
}
*/