package com.jlc.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.jlc.bean.Course;
import com.jlc.bean.PublicParam;
import com.jlc.bean.selectCourse;
import com.jlc.commons.base.BaseController;
import com.jlc.service.CourseService;
import com.jlc.service.PublicParamService;
import com.jlc.service.SelectService;

@Controller
@RequestMapping("/select")
public class SelectCourseController extends BaseController{
	
	@Autowired
    private SelectService scService;
	
	@Autowired
    private CourseService courseService;
	
	@Autowired
	public PublicParamService publicParemService;
	
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
    public String paramManage() {
        return "admin/selectCourse";
    }
	
    /**
     * 插入选课记录
     *
     * @param userVo
     * @return
     */
/*    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public Object add(Course selectedRow) {
        Course u = null;
		try {
			u = scService.findCourseByCourseName(record.getCourseName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if (u != null) {
            return renderError("课程已存在!");
        }
    	
        try {
        	scService.insert(record);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        return renderSuccess("添加成功");
    }*/
    
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
	@ResponseBody
	public Object seveParam(HttpServletRequest request){		
		
		String updatedJason= request.getParameter("selected");
		List<PublicParam> resultList = null;
		Map<String,Object> map = new HashMap<String ,Object>();
		map.put("publicCode", "schoolYear");
		map.put("status", 1);
		try {
			
			updatedJason = new String(updatedJason.getBytes("iso-8859-1"), "utf-8");
			
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		 try {
			
			 List<Course> updateList = JSON.parseArray(updatedJason, Course.class);
			 selectCourse record = new selectCourse();
			 if(updateList.size()>0){
				 for(int i =0;i<updateList.size(); i++){
						System.out.println(updateList.get(i).getId());
						record.setSelectCourseid(updateList.get(i).getId());
						record.setSelectStudentid((long) 1);
						record.setSelectTeacher(updateList.get(i).getCourseTeacher());
						resultList=publicParemService.selectByParam(map);
						record.setselectYear(resultList.get(0).getPublicValueName());
						Course course = courseService.findCourseById(updateList.get(i).getId());
						if(course.getOccupied()+1 <= course.getCapacity()){
							scService.insert(record);
							course.setOccupied(course.getOccupied()+1);
							courseService.updateCourse(course);
						}else{
							return renderError("该课程已满!");
						}
				 }
			 }
			 
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

}
