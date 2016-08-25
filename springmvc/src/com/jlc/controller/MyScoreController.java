package com.jlc.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.jlc.bean.Organization;
import com.jlc.bean.PublicParam;
import com.jlc.bean.Score;
import com.jlc.commons.base.BaseController;
import com.jlc.commons.result.UserVo;
import com.jlc.commons.utils.PageInfo;
import com.jlc.service.ScoreServer;


@Controller
@RequestMapping("/score")
public class MyScoreController extends BaseController{
	@Autowired
	public ScoreServer scoreServer;
	/**
     * 我的cj
     *
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String all() {
        return "admin/MyScore";
    }
    
    /**
     * 我的cj
     *
     * @return
     */
    @RequestMapping(value = "/manage", method = RequestMethod.GET)
    public String manage() {
        return "admin/ScoreManage";
    }
    
    /**
     * 获取成绩信息
     *
     * @return
     */
    @RequestMapping(value = "/myScore", method = RequestMethod.GET)
    @ResponseBody
    public Object dataGrid(String studentId) {
        List<Map<String,Object>> list= new ArrayList<Map<String,Object>>();
        Map<String,Object> map = new HashMap<>();
        map.put("studentId",studentId);
        list = scoreServer.selectScoreByStudentId(map);        
        /*Map<String,Object> map = new HashMap<String,Object>();
        Map<String,Object> map1 = new HashMap<String,Object>();
        Map<String,Object> map2 = new HashMap<String,Object>();
        map.put("courseName", "java");
        map.put("courseClass", "编程");
        map.put("courseCredit", "3");
        map.put("timesScore", "78");
        map.put("testScore", "88");
        map.put("group", "2011~2012学年");
        map.put("editor", "numberbox");
        map.put("value", "74");
        list.add(map);
        
        map2.put("courseName", "C语言");
        map2.put("courseClass", "编程");
        map2.put("courseCredit", "3");
        map2.put("timesScore", "98");
        map2.put("testScore", "88");
        map2.put("group", "2011~2012学年");
        map2.put("editor", "numberbox");
        map2.put("value", "90");
        list.add(map2);
        
        map1.put("courseName", "马克思");
        map1.put("courseClass", "政治");
        map1.put("courseCredit", "2");
        map1.put("timesScore", "90");
        map1.put("testScore", "80");
        map1.put("group", "2012~2013学年");
        map1.put("editor", "numberbox");
        map1.put("value", "80");
        list.add(map1);*/
        return list;
    }
    @RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public void seveParam(HttpServletRequest request){		
		
		String updatedJason= request.getParameter("updated");
	
		try {
			
			updatedJason = new String(updatedJason.getBytes("iso-8859-1"), "utf-8");
			updatedJason = updatedJason.replaceAll("value", "score");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		 try {
			
			 List<Score> updateList = JSON.parseArray(updatedJason, Score.class);
			
			 if(updateList.size()>0){
				 for(int i =0;i<updateList.size(); i++){
					 scoreServer.updateByPrimaryKeySelective(updateList.get(i));
				 }
			 }
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
