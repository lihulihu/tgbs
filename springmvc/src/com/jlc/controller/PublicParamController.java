package com.jlc.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.jlc.bean.PublicParam;
import com.jlc.commons.base.BaseController;
import com.jlc.commons.utils.PageInfo;
import com.jlc.service.PublicParamService;

@Controller
@RequestMapping("/publicParam")
public class PublicParamController extends BaseController{
	@Autowired
	public PublicParamService publicParemService;
	
	
	@RequestMapping(value = "/grade", method = RequestMethod.POST)
    @ResponseBody
    public Object selectGrede() {
		Map<String,Object> map = new HashMap<String ,Object>();
		map.put("publicCode", "grade");
		List<PublicParam> resultList = null;
		try {
			resultList = publicParemService.selectByParam(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return resultList;
    }
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public void seveParam(HttpServletRequest request){		
		String deleteJason= request.getParameter("deleted");
		String updatedJason= request.getParameter("updated");
		String insertedJason= request.getParameter("inserted");
		try {
			deleteJason = new String(deleteJason.getBytes("iso-8859-1"), "utf-8");
			updatedJason = new String(updatedJason.getBytes("iso-8859-1"), "utf-8");
			insertedJason = new String(insertedJason.getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		 try {
			 List<PublicParam> addList = JSON.parseArray(insertedJason, PublicParam.class);
			 List<PublicParam> updateList = JSON.parseArray(updatedJason, PublicParam.class);
			 List<PublicParam> deletedList = JSON.parseArray(deleteJason, PublicParam.class);
			 if(addList.size()>0){
				 for(int i =0;i<addList.size(); i++){
						publicParemService.insert(addList.get(0));
				 }
			 }
			 if(updateList.size()>0){
				 for(int i =0;i<updateList.size(); i++){
						publicParemService.updateByPrimaryKey(updateList.get(i));
				 }
			 }
			 if(deletedList.size()>0){
				 for(int i =0;i<deletedList.size(); i++){
						publicParemService.deleteByPrimaryKey(deletedList.get(i).getPublicId());
				 }
			 }
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.POST)
    @ResponseBody
    public Object selectAllParam(PublicParam publicParam,Integer page, Integer rows) {
		PageInfo pageInfo = new PageInfo(page, rows);
		Map<String,Object> map = new HashMap<String ,Object>();
		if(publicParam.getPublicCode() != null){
			map.put("publicCode", publicParam.getPublicCode());
		}
		if(publicParam.getPublicId() != null){
			map.put("publicId", publicParam.getPublicId());
		}
		pageInfo.setCondition(map);
		
		try {
			publicParemService.selectParamPage(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return pageInfo;
    }
	
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
    public String paramManage() {
        return "admin/param";
    }
}
