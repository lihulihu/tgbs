package com.jlc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jlc.bean.Announcement;
import com.jlc.bean.Course;
import com.jlc.commons.base.BaseController;
import com.jlc.service.AnnouncementService;
@Controller
@RequestMapping("/news")
public class NewsController extends BaseController{
	@Autowired 
	private AnnouncementService announcementService;
	
	 @RequestMapping(value = "/manage", method = RequestMethod.GET)
	 public String manager() {
	     return "admin/organization";
	 }
	 
	 @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
	 @ResponseBody
	 public Object dataGrid1() {
		 Map<String,Object> map = new HashMap<String,Object>();
		 List<Announcement> resultList = null;
		 try {
			 resultList = announcementService.selectList(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	
	 }
	 
	 @RequestMapping(value = "/all", method = RequestMethod.GET)
	 public String dataGrid(Model model) {
		 Map<String,Object> map = new HashMap<String,Object>();
		 List<Announcement> resultList = null;
		 try {
			 resultList = announcementService.selectList(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("news", resultList);
		return "admin/news";
	 }
	    
}
