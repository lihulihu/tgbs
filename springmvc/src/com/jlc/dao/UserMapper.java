package com.jlc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jlc.commons.result.UserVo;
import com.jlc.commons.utils.PageInfo;

public interface UserMapper {
    int deleteByPrimaryKey(Long id) throws Exception;

    int insert(UserVo record) throws Exception;


    UserVo selectByPrimaryKey(Long id) throws Exception;

    int updateByPrimaryKeySelective(UserVo record) throws Exception;

  
    
    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    UserVo findUserByLoginName(String username);
    /**
     * 根据用户id查询用户
     *
     * @param id
     * @return
     */
    UserVo findUserById(Long id);
    /**
     * 用户列表
     *
     * @param pageInfo
     * @return
     */
    List findUserPageCondition(PageInfo pageInfo);
    /**
     * 统计用户
     *
     * @param pageInfo
     * @return
     */
    int findUserPageCount(PageInfo pageInfo);
    void updateUserPwdById(@Param("userId") Long userId, @Param("pwd") String pwd);
    /**
     * 根据用户id查询用户带部门
     *
     * @param id
     * @return
     */

    UserVo findUserVoById(Long id);

}