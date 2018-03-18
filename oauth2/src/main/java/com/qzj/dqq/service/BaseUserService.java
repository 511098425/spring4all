package com.qzj.dqq.service;

import com.qzj.dqq.domain.RoleConstant;
import com.qzj.dqq.domain.SysUser;
import com.qzj.dqq.mapper.SysUserMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Primary
public class BaseUserService implements SysUserService {

    private final SysUserMapper sysUserMapper;

    public BaseUserService(SysUserMapper sysUserMapper){
        this.sysUserMapper = sysUserMapper;
    }

    @Override
    public boolean registUser(SysUser sysUser) {
        String phone = sysUser.getPhone();
        if (exist(phone))
            return false;
        sysUser.setPassword(new BCryptPasswordEncoder().encode(sysUser.getPassword()));
        sysUser.setRole(RoleConstant.ADMIN.getName());
        sysUser.setUserId(dateToStrLong(new Date()));
        int result = sysUserMapper.registUser(sysUser);
        return  result == 1;
    }

    @Override
    public SysUser fetchUserByPhone(String phone) {
        return sysUserMapper.getUserByPhone(phone);
    }

    /**
     * 判断用户是否存在
     * @param phone 账号
     * @return 密码
     */
    private boolean exist(String phone){
        SysUser userInfo = sysUserMapper.getUserByPhone(phone);
        return (userInfo != null);
    }


    public String dateToStrLong(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String dateString = formatter.format(dateDate);
        return dateString;
    }
}
