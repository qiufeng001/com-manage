package com.manage.domain.service;


import com.manage.common.core.core.domain.entity.SysUser;
import com.manage.common.core.core.domain.entity.WechatRole;
import com.manage.common.core.core.domain.entity.WechatUser;

import java.util.List;

/**
 * 用户 业务层
 *
 * @author zhong.h
 */
public interface IWechatUserService {
    /**
     * 根据条件分页查询用户列表
     *
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    public List<WechatUser> selectUserList(WechatUser user);

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    public WechatUser selectUserByUserName(String userName);

    /**
     * 通过用户ID查询用户
     *
     * @param userId 用户ID
     * @return 用户对象信息
     */
    public WechatUser selectUserById(Long userId);

    public int saveOrUpdateUser(WechatUser user);
}
