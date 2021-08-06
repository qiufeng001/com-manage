package com.manage.domain.service.impl;
import com.manage.common.core.annotation.DataScope;
import com.manage.common.core.core.domain.entity.SysUser;
import com.manage.common.core.core.domain.entity.WechatRole;
import com.manage.common.core.core.domain.entity.WechatUser;
import com.manage.common.core.utils.StringUtils;
import com.manage.domain.mapper.*;
import com.manage.domain.service.ISysConfigService;
import com.manage.domain.service.ISysUserService;
import com.manage.domain.service.IWechatUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户 业务层处理
 *
 * @author zhong.h
 */
@Service
public class WechatUserServiceImpl implements IWechatUserService {
    private static final Logger log = LoggerFactory.getLogger(WechatUserServiceImpl.class);

    @Autowired
    private WechatUserMapper userMapper;

    @Autowired
    private WechatRoleMapper roleMapper;

    @Autowired
    private WechatUserRoleMapper userRoleMapper;

    @Autowired
    private ISysConfigService configService;

    @Autowired
    private ISysUserService sysUserService;

    /**
     * 根据条件分页查询用户列表
     *
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<WechatUser> selectUserList(WechatUser user) {
        return userMapper.selectUserList(user);
    }

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    @Override
    public WechatUser selectUserByUserName(String userName) {
        return userMapper.selectUserByUserName(userName);
    }

    /**
     * 通过用户ID查询用户
     *
     * @param userId 用户ID
     * @return 用户对象信息
     */
    @Override
    public WechatUser selectUserById(Long userId) {
        return userMapper.selectUserById(userId);
    }

    /**
     * 新增保存用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    @Transactional
    public int saveOrUpdateUser(WechatUser user) {
        int rows = 0;
        if(user.getId() != null) {
            // 新增用户信息
            rows = userMapper.insertUser(user);
            /// 默认普通用户是管理员权限：1，顾客权限 id:2,普通员工权限：3
            // 根据电话号码查询设置权限
            SysUser sysUser = new SysUser();
            sysUser.setPhonenumber(user.getPhoneNum());
            List<SysUser> sysUsers = sysUserService.seletctByParams(sysUser);
            if(CollectionUtils.isEmpty(sysUsers)) {

            }
            insertUserRole(user);
        }else {
            return userMapper.updateUser(user);
        }
        return rows;
    }

    /**
     * 新增用户角色信息
     *
     * @param user 用户对象
     */
    public void insertUserRole(WechatUser user) {
        Long[] roles = user.getRoleIds();
        if (StringUtils.isNotNull(roles)) {
            // 新增用户与角色管理
            List<WechatRole> list = new ArrayList<WechatRole>();
            for (Long roleId : roles) {
                WechatRole ur = new WechatRole();
                ur.setWxUserId(user.getUserId());
                ur.setRoleId(roleId);
                list.add(ur);
            }
            if (list.size() > 0) {
                userRoleMapper.batchUserRole(list);
            }
        }
    }

}
