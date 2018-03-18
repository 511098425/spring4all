package com.qzj.dqq.handle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qzj.dqq.enums.ResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by qzj on 2018/2/25
 */
public class AjaxLogoutSuccessHandler implements LogoutSuccessHandler {

    private static final Logger logger = LoggerFactory.getLogger(AjaxLogoutSuccessHandler.class);

    private String signOutSuccessUrl;

    private ObjectMapper objectMapper = new ObjectMapper();

    public AjaxLogoutSuccessHandler(String signOutSuccessUrl) {
        this.signOutSuccessUrl = signOutSuccessUrl;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.info("退出成功");
        if ("XMLHttpRequest".equalsIgnoreCase((request).getHeader("X-Requested-With"))) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(ResultEnum.SUCCESS));
        } else {
            response.sendRedirect(signOutSuccessUrl);
        }
    }
}
