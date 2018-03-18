package com.qzj.dqq.service;

import com.qzj.dqq.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService  {

    private final SysUserService sysUserService;

    @Autowired
    CustomUserDetailsService(SysUserService sysUserService){
        this.sysUserService = sysUserService;
    }

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        SysUser sysUser = sysUserService.fetchUserByPhone(phone);
        if (sysUser == null){
            throw new UsernameNotFoundException("用户不存在！");
        }
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = createAuthorities(sysUser.getRole());
        return new User(sysUser.getPhone(), sysUser.getPassword(), simpleGrantedAuthorities);
    }

    /**
     * 权限字符串转化
     * 如 "USER,ADMIN" -> SimpleGrantedAuthority("USER") + SimpleGrantedAuthority("ADMIN")
     * @param roleStr 权限字符串
     */
    private List<SimpleGrantedAuthority> createAuthorities(String roleStr){
        String[] roles = roleStr.split(",");
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        for (String role : roles) {
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority(role));
        }
        return simpleGrantedAuthorities;
    }
   /* @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
       // SysUser sysUser = sysUserService.fetchUserByPhone(userId);
        return new SocialUser(userId,"123456", AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN"));
    }*/
}
