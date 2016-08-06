package com.jlc.service;

import com.jlc.commons.utils.PageInfo;
import com.jlc.commons.result.UserVo;

/**
 * @description：用户管理
 * @author：zhixuan.wang
 * @date：2015/10/1 14:51
 */
public interface UserService {
    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    UserVo findUserByLoginName(String username) throws Exception;

    /**
     * 根据用户id查询用户
     *
     * @param id
     * @return
     */
    UserVo findUserById(Long id) throws Exception;

    /**
     * 用户列表
     *
     * @param pageInfo
     */
    void findDataGrid(PageInfo pageInfo) throws Exception;

    /**
     * 添加用户
     *
     * @param userVo
     */
    void addUser(UserVo userVo) throws Exception;

    /**
     * 修改密码
     *
     * @param userId
     * @param pwd
     */
    void updateUserPwdById(Long userId, String pwd) throws Exception;

    /**
     * 根据用户id查询用户带部门
     *
     * @param id
     * @return
     */
    UserVo findUserVoById(Long id) throws Exception;

    /**
     * 修改用户
     *
     * @param userVo
     */
    void updateUser(UserVo userVo) throws Exception;

    /**
     * 删除用户
     *
     * @param id
     */
    void deleteUserById(Long id) throws Exception;

}
