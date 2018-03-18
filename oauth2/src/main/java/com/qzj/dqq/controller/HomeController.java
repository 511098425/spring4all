package com.qzj.dqq.controller;

import com.qzj.dqq.properties.SecurityConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by qzj on 2018/2/25
 */
@Controller
public class HomeController {


    /**
     * 跳转到登录界面
     * @return
     */
    @GetMapping(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
    public String require() {
        return "login";
    }

    /**
     * 手机号注册
     * @return
     */
    @GetMapping(SecurityConstants.DEFAULT_REGISTER_URL)
    public String mobilePage() {

        return "register";
    }

    /**
     * session失效的跳转
     * @return
     */
    @GetMapping("/auth")
    public String sessionInvalid(){
        return "sso";
    }
}
