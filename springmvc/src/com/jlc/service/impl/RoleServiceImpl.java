package com.jlc.service.impl;

import com.jlc.commons.exception.ServiceException;
import com.jlc.dao.RoleMapper;
import com.jlc.dao.RoleResourceMapper;
import com.jlc.dao.UserRoleMapper;
import com.jlc.bean.Role;
import com.jlc.bean.RoleResource;
import com.jlc.service.RoleService;
import com.jlc.commons.utils.PageInfo;
import com.jlc.commons.result.Tree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {

    private static Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleResourceMapper roleResourceMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    public void findDataGrid(PageInfo pageInfo) {
        pageInfo.setRows(roleMapper.findRolePageCondition(pageInfo));
        pageInfo.setTotal(roleMapper.findRolePageCount(pageInfo));
    }

    public List<Tree> findTree() {
        List<Tree> trees = new ArrayList<Tree>();
        List<Role> roles = roleMapper.findRoleAll();
        for (Role role : roles) {
            Tree tree = new Tree();
            tree.setId(role.getId());
            tree.setText(role.getName());

            trees.add(tree);
        }
        return trees;
    }

    public void addRole(Role role) {
        int insert = roleMapper.insert(role);
        if (insert != 1) {
            LOGGER.warn("插入失败，参数：{}", role.toString());
            throw new ServiceException("插入失败");
        }
    }

    public void deleteRoleById(Long id) {
        int update = roleMapper.deleteRoleById(id);
        if (update != 1) {
            LOGGER.warn("删除失败，id：{}", id);
            throw new ServiceException("删除失败");
        }
    }

    public Role findRoleById(Long id) {
        return roleMapper.findRoleById(id);
    }

    public void updateRole(Role role) {
        int update = roleMapper.updateRole(role);
        if (update != 1) {
            LOGGER.warn("更新失败，参数：{}", role.toString());
            throw new ServiceException("更新失败");
        }
    }

    public List<Long> findResourceIdListByRoleId(Long id) {
        return roleMapper.findResourceIdListByRoleId(id);
    }

    public void updateRoleResource(Long id, String resourceIds) {
        // 先删除后添加,有点爆力
        List<Long> roleResourceIdList = roleMapper.findRoleResourceIdListByRoleId(id);
        if (roleResourceIdList != null && (!roleResourceIdList.isEmpty())) {
            for (Long roleResourceId : roleResourceIdList) {
                roleResourceMapper.deleteById(roleResourceId);
            }
        }
        String[] resources = resourceIds.split(",");
        RoleResource roleResource = new RoleResource();
        for (String string : resources) {
            roleResource.setRoleId(id);
            roleResource.setResourceId(Long.parseLong(string));
            roleResourceMapper.insert(roleResource);
        }
    }

    public List<Long> findRoleIdListByUserId(Long userId) {
        return userRoleMapper.findRoleIdListByUserId(userId);
    }

    public List<Map<Long, String>> findRoleResourceListByRoleId(Long roleId) {
        return roleMapper.findRoleResourceListByRoleId(roleId);
    }

}
