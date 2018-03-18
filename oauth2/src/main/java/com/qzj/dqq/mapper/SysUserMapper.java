package com.qzj.dqq.mapper;

import com.qzj.dqq.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

    @Mapper
    @Component
    public interface SysUserMapper {

    int registUser(SysUser sysUser);

    SysUser getUserByPhone(@Param("phone") String phone);

}
