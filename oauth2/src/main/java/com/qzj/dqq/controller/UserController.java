package com.qzj.dqq.controller;

import com.aliyuncs.exceptions.ClientException;
import com.qzj.dqq.aliyun.AliyunSMS;
import com.qzj.dqq.domain.SysUser;
import com.qzj.dqq.enums.ResultEnum;
import com.qzj.dqq.properties.SecurityConstants;
import com.qzj.dqq.service.AutomicLogin;
import com.qzj.dqq.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 2018/1/4.
 *
 * @author zlf
 * @since 1.0
 */
@Controller
public class UserController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private AutomicLogin automicLogin;

    @PostMapping(SecurityConstants.DEFAULT_REGISTER_URL)
    @ResponseBody
    public String register(HttpServletRequest httpServletRequest) {
        SysUser sysUser = new SysUser();
        //从请求中获取手机号码
        String alicode = httpServletRequest.getParameter(SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_SMS);
        String mobile = httpServletRequest.getParameter(SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE);
        String password = httpServletRequest.getParameter(SecurityConstants.DEFAULT_PARAMETER_NAME_PASSWORD);
        //进行验证码对比校验
        try {
            String code = AliyunSMS.querySendDetails(mobile);
            if (code.equals(alicode)){
                sysUser.setPhone(mobile);
                sysUser.setPassword(password);
                if (sysUserService.registUser(sysUser)){
                    automicLogin.shili(httpServletRequest,sysUser.getPhone(),password);
                    return ResultEnum.SUCCESS.getCode().toString();
                }
            }else {
                return ResultEnum.FAIL.getCode().toString();
            }
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return ResultEnum.FAIL.getCode().toString();
    }

}
