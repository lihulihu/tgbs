package com.jlc.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jlc.bean.Announcement;
import com.jlc.bean.Course;
import com.jlc.bean.Organization;
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
	    
	 
	 /**
	     * 编辑页
	     *
	     * @param request
	     * @param id
	     * @return
	     */
	    @RequestMapping("/editPage")
	    public String editPage(HttpServletRequest request, Long id) {
	    	Announcement announcement = null;
			try {
				announcement = announcementService.selectByPrimaryKey(new Integer(id.toString()));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
	        request.setAttribute("announcement", announcement);
	        return "admin/organizationEdit";
	    }
	    
	    /**
	     * 编辑提交
	     *
	     * @param organization
	     * @return
	     */
	    @RequestMapping("/edit")
	    @ResponseBody
	    public Object edit(Announcement announcement) {
	    	try {
				announcementService.updateByPrimaryKeySelective(announcement);
			} catch (Exception e) {
				e.printStackTrace();
			}
	        return renderSuccess("编辑成功！");
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
				announcementService.deleteByPrimaryKey(new Integer(id.toString()));
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
	        return "admin/organizationAdd";
	    }

	    /**
	     * 保存
	     *
	     * @param organization
	     * @return
	     */
	    @RequestMapping("/add")
	    @ResponseBody
	    public Object add(Announcement announcement) {
	    	try {
	    		announcement.setAnnouncementTotal(new Integer(10));
	    		announcement.setAnnouncementDate(new Date());
				announcementService.insert(announcement);
			} catch (Exception e) {
				e.printStackTrace();
			}
	        return renderSuccess("添加成功！");
	    }
}
