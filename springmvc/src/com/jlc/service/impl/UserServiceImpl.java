package com.jlc.service.impl;

import com.jlc.dao.SrItemMapper;
import com.jlc.dao.UserMapper;
import com.jlc.dao.UserRoleMapper;
import com.jlc.bean.Course;
import com.jlc.bean.SrItem;
import com.jlc.bean.UserRole;
import com.jlc.service.UserService;
import com.jlc.commons.utils.PageInfo;
import com.jlc.commons.result.UserVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    
    @Autowired
    private SrItemMapper srItemMapper;

    public UserVo findUserByLoginName(String username) throws Exception{
        return userMapper.findUserByLoginName(username);
    }

    public UserVo findUserById(Long id) {
        return userMapper.findUserById(id);
    }

    public void findDataGrid(PageInfo pageInfo) throws Exception{
        pageInfo.setRows(userMapper.findUserPageCondition(pageInfo));
        pageInfo.setTotal(userMapper.findUserPageCount(pageInfo));
    }

    public void addUser(UserVo userVo) throws Exception{
        
        userMapper.insert(userVo);

        Long id = userVo.getId();
        String[] roles = userVo.getRoleIds().split(",");
        UserRole userRole = new UserRole();

        for (String string : roles) {
            userRole.setUserId(id);
            userRole.setRoleId(Long.valueOf(string));
            userRoleMapper.insert(userRole);
        }
        SrItem srItem = new SrItem();
    	srItem.setsSrId(new Integer(userVo.getSrId()));
    	srItem.setsSrStudentid(new Integer(userVo.getId().toString()));
    }

    public void updateUserPwdById(Long userId, String pwd) throws Exception{
        userMapper.updateUserPwdById(userId, pwd);
    }

    public UserVo findUserVoById(Long id) throws Exception{
    	 Map<String,Object> map = new HashMap<String,Object> ();
         map.put("sSrStudentid", id);
         List<SrItem> resultList= srItemMapper.selectByMap(map);
         UserVo uservo = userMapper.findUserVoById(id);
         if(resultList!= null && resultList.size()>0){
        	 uservo.setSrId(resultList.get(0).getsSrId().toString());
         }
         
        return  uservo;
    }

    public void updateUser(UserVo userVo) throws Exception{
       
        userMapper.updateByPrimaryKeySelective(userVo);
        if(userVo.getRoleIds() != null && !"".equals(userVo.getRoleIds())){
        	 Long id = userVo.getId();
             List<UserRole> userRoles = userRoleMapper.findUserRoleByUserId(id);
             if (userRoles != null && (!userRoles.isEmpty())) {
                 for (UserRole userRole : userRoles) {
                     userRoleMapper.deleteById(userRole.getId());
                 }
             }

             String[] roles = userVo.getRoleIds().split(",");
             UserRole userRole = new UserRole();
             for (String string : roles) {
                 userRole.setUserId(id);
                 userRole.setRoleId(Long.valueOf(string));
                 userRoleMapper.insert(userRole);
             }
        }
       
        Map<String,Object> map = new HashMap<String,Object> ();
        map.put("sSrStudentid", userVo.getId());
        List<SrItem> resultList= srItemMapper.selectByMap(map);
        if(resultList!= null && resultList.size()>0){
        	SrItem srItem = resultList.get(0);
        	srItem.setsSrId(new Integer(userVo.getSrId()));
        	srItemMapper.updateByPrimaryKeySelective(srItem);
        }else{
        	SrItem srItem = new SrItem();
        	srItem.setsSrId(new Integer(userVo.getSrId()));
        	srItem.setsSrStudentid(new Integer(userVo.getId().toString()));
        }
        
       

    }

    public void deleteUserById(Long id) throws Exception{
        userMapper.deleteByPrimaryKey(id);
        List<UserRole> userRoles = userRoleMapper.findUserRoleByUserId(id);
        if (userRoles != null && (!userRoles.isEmpty())) {
            for (UserRole userRole : userRoles) {
                userRoleMapper.deleteById(userRole.getId());
            }
        }
        Map<String,Object> map = new HashMap<String,Object> ();
        map.put("sSrStudentid", id);
        List<SrItem> resultList= srItemMapper.selectByMap(map);
        if(resultList != null && resultList.size()>0){
        	srItemMapper.deleteByPrimaryKey(resultList.get(0).getsSrId());
        }
    }
    

    public List<Course> findSelectedCourseById(Long id) throws Exception {
    	List<Course> courselist= userMapper.findSelectedCourseById(id);
    	System.out.println("用户已选"+courselist.size()+"门课程");
    	return courselist;
	}

}
