package com.qzj.dqq.handle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qzj.dqq.enums.ResultEnum;
import com.qzj.dqq.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义登录失败处理
 * Created by qzj on 2018/2/2
 */
@Component
public class AjaxLoginAuthFailHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().write(objectMapper.writeValueAsString(ResultUtil.error(ResultEnum.AUTHENTICATION_ERROR.getCode(), ResultEnum.AUTHENTICATION_ERROR.getMsg())));
    }
}
