package com.jlc.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jlc.bean.Announcement;
import com.jlc.bean.Sr;
import com.jlc.bean.SrItem;
import com.jlc.commons.base.BaseController;
import com.jlc.commons.result.UserVo;
import com.jlc.service.SrItemService;
import com.jlc.service.SrService;
@Controller
@RequestMapping("/sr")
public class SrController extends BaseController{
	@Autowired 
	private SrService srService;
	
	@Autowired
	private SrItemService srItemService;
	
	 @RequestMapping(value = "/manage", method = RequestMethod.GET)
	 public String manager() {
	     return "admin/srManage";
	 }
	 
	 @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
	 @ResponseBody
	 public Object dataGrid1() {
		 Map<String,Object> map = new HashMap<String,Object>();
		 List<Sr> resultList = null;
		 try {
			 resultList = srService.selectList(map);
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
			 //resultList = srService.selectList(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("sr", resultList);
		return "admin/news";
	 }
	    
	 
	 /**
	     * 编辑页
	     *
	     * @param request
	     * @param id
	     * @return
	     */
	    @RequestMapping("/editPage")
	    public String editPage(HttpServletRequest request, Long id) {
	    	Sr sr = null;
			try {
				sr = srService.selectByPrimaryKey(new Integer(id.toString()));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
	        request.setAttribute("sr", sr);
	        return "admin/SrEdit";
	    }
	    
	    /**
	     * 编辑提交
	     *
	     * @param organization
	     * @return
	     */
	    @RequestMapping("/edit")
	    @ResponseBody
	    public Object edit(Sr sr) {
	    	try {
				srService.updateByPrimaryKeySelective(sr);
			} catch (Exception e) {
				e.printStackTrace();
			}
	        return renderSuccess("保存成功！");
	    }
	    
	    
	    /**
	     * 删除
	     *
	     * @param id
	     * @return
	     */
	    @RequestMapping("/delete")
	    @ResponseBody
	    public Object delete(Long id) {
	    	try {
				srService.deleteByPrimaryKey(new Integer(id.toString()));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
	        return renderSuccess("删除成功！");
	    }
	    
	    
	    /**
	     * 添加
	     *
	     * @return
	     */
	    @RequestMapping("/addPage")
	    public String addPage() {
	        return "admin/SrAdd";
	    }

	    /**
	     * 保存
	     *
	     * @param organization
	     * @return
	     */
	    @RequestMapping("/add")
	    @ResponseBody
	    public Object add(Sr sr) {
	    	try {
	    		//sr.setAnnouncementTotal(new Integer(10));
	    		//sr.setAnnouncementDate(new Date());
				srService.insert(sr);
			} catch (Exception e) {
				e.printStackTrace();
			}
	        return renderSuccess("添加成功！");
	    }
	    
	    
	    /**
	     * 我的科研项目
	     *
	     * @param request
	     * @param id
	     * @return
	     */
	    @RequestMapping("/mysr")
	    public String mysr(HttpServletRequest request, Long id) {
	    	Map<String,Object> mysr = null;
	    	Map<String,Object> map = new HashMap<String,Object>();
	    	Subject subject = SecurityUtils.getSubject();
        	Session session = subject.getSession();
        	UserVo sessionUser = (UserVo)session.getAttribute("user");
        	
	    	map.put("studentId",sessionUser.getId());
			try {
				mysr = srService.selectByUserId(map);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
	        request.setAttribute("mysr", mysr);
	        return "admin/MySr";
	    }
	    
	    @RequestMapping("/updatesr")
	    @ResponseBody
	    public Object updatesr(SrItem item1) {
		    	    	    	
			try {
				srItemService.updateByPrimaryKeySelective(item1);
				
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "保存成功";
	    }
	    
}
