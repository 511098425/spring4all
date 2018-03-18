package com.qzj.dqq.enums;

/**
 * Created by qzj on 2018/2/25
 */
public enum ResultEnum {
        UNKONW_ERROR(-1, "未知错误"),
        SUCCESS(0, "成功"),
        AUTHENTICATION_ERROR(1,"用户名或密码错误"),
        FAIL(-2, "失败"),
        CODE_ERROT(103,"验证码验证失败"),
        SESSION_INVALID(104,"session失效");

        private Integer code;

        private String message;

        ResultEnum(Integer code, String message) {
            this.code = code;
            this.message = message;
        }

        public Integer getCode() {
            return code;
        }

        public String getMsg() {
            return message;
        }
    }
