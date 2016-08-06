package com.jlc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jlc.commons.base.BaseController;
@Controller
@RequestMapping("/student")
public class StudentMessageController extends BaseController{
	 /**
     * 学生信息管理页
     *
     * @return
     */
    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public String studentMessage() {
        return "student/studentMessage";
    }
}
