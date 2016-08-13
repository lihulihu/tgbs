package com.jlc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;






import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUpload;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.jlc.commons.base.BaseController;
import com.jlc.commons.result.UserVo;
import com.jlc.service.UserService;
@Controller
@RequestMapping("/info")
public class PhotoUploadController extends BaseController{
	
	@Autowired
    private UserService userService;
	@RequestMapping("/upload")  
    public String upload(HttpServletRequest request, HttpServletResponse response) throws IOException {  
	 
           
       
      //解析器解析request的上下文
        CommonsMultipartResolver multipartResolver = 
          new CommonsMultipartResolver(request.getSession().getServletContext()); 
        //先判断request中是否包涵multipart类型的数据，
        if(multipartResolver.isMultipart(request)){
        	//再将request中的数据转化成multipart类型的数据
        	MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        	Iterator iter = multiRequest.getFileNames();
        	String Userpath = "";
        	while(iter.hasNext()){
        		MultipartFile file = multiRequest.getFile((String)iter.next());
        		if(file != null){
        			String fileName = file.getOriginalFilename();
        			// String path = "D:/" + fileName;
        			String path = "E:\\hlb\\.metadata\\.me_tcat7\\webapps\\springmvc\\static\\photo\\" +fileName;
        			Userpath = "/static/photo/"+fileName;
        			File localFile = new File(path);          
        			//写文件到本地
        			file.transferTo(localFile);
        		}
        	}
        	UserVo user = new UserVo();
        	Subject subject = SecurityUtils.getSubject();
        	Session session = subject.getSession();
        	UserVo sessionUser = (UserVo)session.getAttribute("user");
        	sessionUser.setPhoto(Userpath);
        	session.setAttribute("user",sessionUser);
        	user.setId(sessionUser.getId());
        	user.setPhoto(Userpath);
        	try {
				userService.updateUser(user);
			} catch (Exception e) {
				e.printStackTrace();
			}
        	
        } 
        return "admin/myInfo";
    }
}
