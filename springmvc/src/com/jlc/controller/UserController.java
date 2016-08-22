package com.jlc.controller;

import com.jlc.commons.base.BaseController;
import com.jlc.commons.result.UserVo;
import com.jlc.commons.utils.PageInfo;
import com.jlc.bean.Organization;
import com.jlc.bean.Role;
import com.jlc.service.OrganizationService;
import com.jlc.service.UserService;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
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

/**
 * @description：用户管理
 * @author：zhixuan.wang
 * @date：2015/10/1 14:51
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrganizationService organizationService;
    /**
     * 用户管理页
     *
     * @return
     */
    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public String manager() {
        return "admin/user";
    }

    /**
     * 我的信息
     *
     * @return
     */
    @RequestMapping(value = "/myInfo", method = RequestMethod.GET)
    public String myInfo() {
        return "admin/myInfo";
    }
    
    
    /**
     * 学生信息管理页
     *
     * @return
     */
    @RequestMapping(value = "/student/message", method = RequestMethod.GET)
    public String studentMessage() {
        return "admin/studentMessage";
    }

    /**
     * 用户管理列表
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
    public Object dataGrid(UserVo userVo, Integer page, Integer rows, String sort, String order) {
        PageInfo pageInfo = new PageInfo(page, rows);
        Map<String, Object> condition = new HashMap<String, Object>();

        if (StringUtils.isNotBlank(userVo.getName())) {
            condition.put("name", userVo.getName());
        }
        if (userVo.getOrganizationId() != null) {
        	Organization organization = organizationService.findOrganizationById(userVo.getOrganizationId().longValue());
        	List<Long> list = new ArrayList<>();
        	if(organization != null && organization.getPid() == null){
        		List<Organization> organizationList = organizationService.findOrganizationAllByPid(userVo.getOrganizationId().longValue());
        		for(int i = 0; i < organizationList.size(); i++){
        			list.add(organizationList.get(i).getId());
        		}
        	}
        	else{
        		list.add(userVo.getOrganizationId().longValue());
        	}
        		
            condition.put("organizationId", list);
        }
        if (userVo.getCreatedateStart() != null) {
            condition.put("startTime", userVo.getCreatedateStart());
        }
        if (userVo.getCreatedateEnd() != null) {
            condition.put("endTime", userVo.getCreatedateEnd());
        }
        pageInfo.setCondition(condition);
        try {
			userService.findDataGrid(pageInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return pageInfo;
    }

    /**
     * 添加用户页
     *
     * @return
     */
    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public String addPage() {
        return "admin/userAdd";
    }

    /**
     * 添加用户
     *
     * @param userVo
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(UserVo userVo) {
        UserVo u = null;
		try {
			u = userService.findUserByLoginName(userVo.getLoginname());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if (u != null) {
            return renderError("用户名已存在!");
        }
        userVo.setPassword(DigestUtils.md5Hex(userVo.getPassword()));
        try {
			userService.addUser(userVo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return renderSuccess("添加成功");
    }

    
    /**
     * 查看用户页
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/lookPage")
    public String lookPage(Long id, Model model) {
        UserVo userVo = null;
		try {
			userVo = userService.findUserVoById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        List<Role> rolesList = userVo.getRolesList();
        List<Long> ids = new ArrayList<Long>();
        for (Role role : rolesList) {
            ids.add(role.getId());
        }
        model.addAttribute("roleIds", ids);
        model.addAttribute("user", userVo);
        return "admin/message";
    }
    /**
     * 编辑用户页
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/editPage")
    public String editPage(Long id, Model model) {
        UserVo userVo = null;
		try {
			userVo = userService.findUserVoById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        List<Role> rolesList = userVo.getRolesList();
        List<Long> ids = new ArrayList<Long>();
        for (Role role : rolesList) {
            ids.add(role.getId());
        }
        model.addAttribute("roleIds", ids);
        model.addAttribute("user", userVo);
        return "admin/userEdit";
    }

    /**
     * 编辑用户
     *
     * @param userVo
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(UserVo userVo) {
        UserVo user = null;
		try {
			user = userService.findUserByLoginName(userVo.getLoginname());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if (user != null && user.getId() != userVo.getId()) {
            return renderError("用户名已存在!");
        }
        //userVo.setPassword(DigestUtils.md5Hex(userVo.getPassword()));
        try {
			userService.updateUser(userVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return renderSuccess("修改成功！");
    }

    /**
     * 保存用户
     *
     * @param userVo
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public Object update(UserVo userVo,Model model) {
    	Subject subject = SecurityUtils.getSubject();
    	Session session = subject.getSession();
    	UserVo sessionUser = (UserVo)session.getAttribute("user");
    	userVo.setId(sessionUser.getId());
        try {
			userService.updateUser(userVo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	        
    	UserVo user = null;
		try {
			user = userService.findUserByLoginName(sessionUser.getLoginname());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.setAttribute("user",sessionUser);
		model.addAttribute("msg", "保存成功");
        return "admin/myInfo";
    }
    
    
    /**
     * 修改密码页
     *
     * @return
     */
    @RequestMapping(value = "/editPwdPage", method = RequestMethod.GET)
    public String editPwdPage() {
        return "admin/userEditPwd";
    }

    /**
     * 修改密码
     *
     * @param oldPwd
     * @param pwd
     * @return
     */
    @RequestMapping("/editUserPwd")
    @ResponseBody
    public Object editUserPwd(String oldPwd, String pwd) {
        if (!getCurrentUser().getPassword().equals(DigestUtils.md5Hex(oldPwd))) {
            return renderError("老密码不正确!");
        }

        try {
			userService.updateUserPwdById(getUserId(), DigestUtils.md5Hex(pwd));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return renderSuccess("密码修改成功！");
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(Long id) {
        try {
			userService.deleteUserById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return renderSuccess("删除成功！");
    }
}
