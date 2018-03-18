package com.qzj.dqq.service;


import com.qzj.dqq.domain.SysUser;

public interface SysUserService {

    /**
     * 添加新用户
     * username 唯一， 默认 USER 权限
     */
    boolean registUser(SysUser sysUser);

    /**
     * 查询用户信息
     * @param phone 账号
     * @return SysUser
     */
    SysUser fetchUserByPhone(String phone);

}
