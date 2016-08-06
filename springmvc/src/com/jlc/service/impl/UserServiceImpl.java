package com.jlc.service.impl;

import com.jlc.dao.UserMapper;
import com.jlc.dao.UserRoleMapper;
import com.jlc.bean.UserRole;
import com.jlc.service.UserService;
import com.jlc.commons.utils.PageInfo;
import com.jlc.commons.result.UserVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

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

       /* Long id = userVo.getId();
        String[] roles = userVo.getRoleIds().split(",");
        UserRole userRole = new UserRole();

        for (String string : roles) {
            userRole.setUserId(id);
            userRole.setRoleId(Long.valueOf(string));
            userRoleMapper.insert(userRole);
        }*/
    }

    public void updateUserPwdById(Long userId, String pwd) throws Exception{
        userMapper.updateUserPwdById(userId, pwd);
    }

    public UserVo findUserVoById(Long id) throws Exception{
        return userMapper.findUserVoById(id);
    }

    public void updateUser(UserVo userVo) throws Exception{
       
        userMapper.updateByPrimaryKeySelective(userVo);
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

    public void deleteUserById(Long id) throws Exception{
        userMapper.deleteByPrimaryKey(id);
        List<UserRole> userRoles = userRoleMapper.findUserRoleByUserId(id);
        if (userRoles != null && (!userRoles.isEmpty())) {
            for (UserRole userRole : userRoles) {
                userRoleMapper.deleteById(userRole.getId());
            }
        }
    }

}
