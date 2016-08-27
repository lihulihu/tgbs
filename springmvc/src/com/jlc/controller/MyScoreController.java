package com.jlc.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
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
					 if(updateList.get(i).getScoreId() == null){
						 scoreServer.insertSelective(updateList.get(i));
					 }else{
						 scoreServer.updateByPrimaryKeySelective(updateList.get(i));
					 }
					 
				 }
			 }
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    
    
    /**
     * 获取登录人成绩信息
     *
     * @return
     */
    @RequestMapping(value = "/myScore1", method = RequestMethod.GET)
    @ResponseBody
    public Object dataGrid1() {
        List<Map<String,Object>> list= new ArrayList<Map<String,Object>>();
        Map<String,Object> parammap = new HashMap<>();
        Subject subject = SecurityUtils.getSubject();
    	Session session = subject.getSession();
    	UserVo sessionUser = (UserVo)session.getAttribute("user");
        parammap.put("studentId",sessionUser.getId());
        list = scoreServer.selectScoreByStudentId1(parammap);        
        return list;
    }
}
