package com.qzj.dqq.validate;

import com.qzj.dqq.enums.ResultEnum;
import com.qzj.dqq.exception.ValidateCodeException;
import com.qzj.dqq.properties.SecurityConstants;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2018/1/7 0007.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
@Component("validateCodeFilter")
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {


    /**
     * 存放所有需要检验验证码得url
     */
    private List<String> urlList = new ArrayList<>();

    /**
     * 验证请求url与配置得url是否匹配得工具类
     */
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    /**
     * 初始化bena完成后该信息也就是拦截得url
     * @throws ServletException
     */
    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        urlList.add(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM);

    }

    /**
     * 讲系统中配置的需要校验验证码的URL根据校验的类型放入list
     * @param urlString
     */
    protected void addUrlToList(String urlString) {
        if (StringUtils.isNotBlank(urlString)) {
            String[] urls = StringUtils.splitByWholeSeparatorPreserveAllTokens(urlString, ",");
            for (String url : urls) {
                urlList.add(url);
            }
        }
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String url = getValidateCodeType(request);
        if (url != null) {
            logger.info("校验请求(" + request.getRequestURI() + ")中的验证码");
            try {
                String imageCode = request.getParameter(SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_IMAGE);
                String  validateCode= (String) request.getSession().getAttribute(SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_IMAGE);
                if (imageCode.equals(validateCode)){
                    logger.info("验证码校验通过");
                }else {
                    throw new ValidateCodeException(ResultEnum.CODE_ERROT);
                }
            } catch (ValidateCodeException exception) {
                throw new ValidateCodeException(ResultEnum.CODE_ERROT);
            }
        }

        filterChain.doFilter(request, response);
    }

    /**
     * 获取校验码的类型，如果当前请求不需要校验，则返回null
     *
     * @param request
     * @return
     */
    private String getValidateCodeType(HttpServletRequest request) {
        String result = null;
        String requestUrl = request.getRequestURI();
        if (StringUtils.equalsIgnoreCase(request.getMethod(), "post")) {
            for (String url : urlList) {
                if (pathMatcher.match(url, requestUrl)) {
                    result = url;
                }
            }
        }
        return result;
    }
}
