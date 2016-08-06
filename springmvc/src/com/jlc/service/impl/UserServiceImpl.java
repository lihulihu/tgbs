package com.jlc.service.impl;

import com.jlc.dao.UserMapper;
import com.jlc.dao.UserRoleMapper;
import com.jlc.bean.User;
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

    public User findUserByLoginName(String username) {
        return userMapper.findUserByLoginName(username);
    }

    public User findUserById(Long id) {
        return userMapper.findUserById(id);
    }

    public void findDataGrid(PageInfo pageInfo) {
        pageInfo.setRows(userMapper.findUserPageCondition(pageInfo));
        pageInfo.setTotal(userMapper.findUserPageCount(pageInfo));
    }

    public void addUser(UserVo userVo) {
        User user = new User();
        try {
           // PropertyUtils.copyProperties(user, userVo);
        } catch (Exception e) {
            LOGGER.error("类转换异常：{}", e);
            throw new RuntimeException("类型转换异常：{}", e);
        }
        userMapper.insert(user);

        Long id = user.getId();
        String[] roles = userVo.getRoleIds().split(",");
        UserRole userRole = new UserRole();

        for (String string : roles) {
            userRole.setUserId(id);
            userRole.setRoleId(Long.valueOf(string));
            userRoleMapper.insert(userRole);
        }
    }

    public void updateUserPwdById(Long userId, String pwd) {
        userMapper.updateUserPwdById(userId, pwd);
    }

    public UserVo findUserVoById(Long id) {
        return userMapper.findUserVoById(id);
    }

    public void updateUser(UserVo userVo) {
        User user = new User();
        try {
            //PropertyUtils.copyProperties(user, userVo);
        } catch (Exception e) {
            LOGGER.error("类转换异常：{}", e);
            throw new RuntimeException("类型转换异常：{}", e);
        }
        userMapper.updateByPrimaryKey(user);
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

    public void deleteUserById(Long id) {
        userMapper.deleteByPrimaryKey(id);
        List<UserRole> userRoles = userRoleMapper.findUserRoleByUserId(id);
        if (userRoles != null && (!userRoles.isEmpty())) {
            for (UserRole userRole : userRoles) {
                userRoleMapper.deleteById(userRole.getId());
            }
        }
    }

}
