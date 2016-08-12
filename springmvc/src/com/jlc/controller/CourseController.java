package com.jlc.controller;

import com.jlc.commons.base.BaseController;
import com.jlc.bean.Course;
import com.jlc.commons.result.UserVo;
import com.jlc.commons.utils.PageInfo;
import com.jlc.bean.Organization;
import com.jlc.bean.Role;
import com.jlc.service.OrganizationService;
import com.jlc.service.CourseService;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/course")
public class CourseController extends BaseController {
    
	@Autowired
    private CourseService courseService;

    @Autowired
    private OrganizationService organizationService;
    /**
     * 课程管理页
     *
     * @return
     */
    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public String manager() {
        return "admin/course";
    }
    
    /**
     * 课程信息管理页
     *
     * @return
     */
    @RequestMapping(value = "/coursemessage", method = RequestMethod.GET)
    public String studentMessage() {
        return "admin/courseMessage";
    }
    
    /**
     * 课程管理列表
     *
     * @param userVo
     * @param page
     * @param rows
     * @param sort
     * @param order
     * @return
     */
    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public Object dataGrid(Course course, Integer page, Integer rows, String sort, String order) {
        PageInfo pageInfo = new PageInfo(page, rows);
        Map<String, Object> condition = new HashMap<String, Object>();

        if (StringUtils.isNotBlank(course.getCourseName())) {
            condition.put("name", course.getCourseName());
        }

        if (course.getDescription() != null) {
            condition.put("endTime", course.getDescription());
        }
        if (course.getOrganizationId() != null) {
        	Organization organization = organizationService.findOrganizationById(course.getOrganizationId().longValue());
        	List<Long> list = new ArrayList<>();
        	if(organization != null && organization.getPid() == null){
        		List<Organization> organizationList = organizationService.findOrganizationAllByPid(course.getOrganizationId().longValue());
        		for(int i = 0; i < organizationList.size(); i++){
        			list.add(organizationList.get(i).getId());
        		}
        	}
        	else{
        		list.add(course.getOrganizationId().longValue());
        	}
        		
            condition.put("organizationId", list);
        }
        pageInfo.setCondition(condition);
        try {
			courseService.findDataGrid(pageInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return pageInfo;
    }
    
    /**
     * 添加用户
     *
     * @param userVo
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(Course course) {
        Course u = null;
		try {
			u = courseService.findCourseByCourseName(course.getCourseName());
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
        	courseService.addCourse(course);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return renderSuccess("添加成功");
    }
    /**
     * 添加课程页
     *
     * @return
     */
    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public String addPage() {
        return "admin/courseAdd";
    }
    
    /**
     * 编辑课程页
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/editPage")
    public String editPage(Long id, Model model) {
        Course course = null;
		try {
			course = courseService.findCourseById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        model.addAttribute("course", course);
        return "admin/courseEdit";
    }
    
    /**
     * 编辑课程
     *
     * @param userVo
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(Course course) {
    	Course course1 = null;
		try {
			course1 = courseService.findCourseByCourseName(course.getCourseName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if (course1 != null && course1.getId() != course.getId()) {
            return renderError("用户名已存在!");
        }
        
        try {
        	courseService.updateCourse(course);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return renderSuccess("修改成功！");
    }
}
