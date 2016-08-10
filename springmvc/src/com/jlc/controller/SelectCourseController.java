package com.jlc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jlc.commons.base.BaseController;

@Controller
@RequestMapping("/select")
public class SelectCourseController extends BaseController{
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
    public String paramManage() {
        return "admin/selectCourse";
    }
}
