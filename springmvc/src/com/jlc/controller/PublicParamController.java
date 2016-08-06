package com.jlc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jlc.bean.PublicParam;
import com.jlc.commons.base.BaseController;
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
}
