package com.qzj.dqq.config;

import com.qzj.dqq.mobile.SmsCodeAuthenticationSecurityConfig;
import com.qzj.dqq.properties.SecurityConstants;
import com.qzj.dqq.service.CustomUserDetailsService;
import com.qzj.dqq.validate.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)

public class SecurityConfig extends WebSecurityConfigurerAdapter{

     @Autowired
     DataSource dataSource;

     @Autowired
     ValidateCodeSecurityConfig validateCodeSecurityConfig;

     @Autowired
     CustomUserDetailsService customUserDetailsService;

     @Autowired
     SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    RedisConnectionFactory redisConnectionFactory;

   // AjaxLogoutSuccessHandler ajaxLogoutSuccessHandler = new AjaxLogoutSuccessHandler();
    /**
     * 记住我功能的token存取器配置
     * @return
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }

    @Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }
    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void  configure(HttpSecurity http) throws Exception{
        http
                .requestMatchers()
                .antMatchers("/auth/form","/auth/require", "/oauth/authorize")
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                .loginProcessingUrl(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM)
                .permitAll()
                .and()
                .apply(validateCodeSecurityConfig)
                .and()
                .apply(smsCodeAuthenticationSecurityConfig)
                .and()
                .rememberMe()
                .userDetailsService(userDetailsService())
                .tokenRepository(persistentTokenRepository())
                .key("unique-and-secret")
                .rememberMeCookieName("remember-me-cookie-name")
                .tokenValiditySeconds(24 * 60 * 60)
                .and()
                .logout()
                .logoutUrl("/oauth/token")//默认退出地址/logout
                //.logoutSuccessUrl("/index")//退出之后跳转到注册页面
                //.logoutSuccessHandler(ajaxLogoutSuccessHandler)
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .and()
                .csrf().disable();

   }

}