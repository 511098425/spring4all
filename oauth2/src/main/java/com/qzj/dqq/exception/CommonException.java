package com.qzj.dqq.exception;


import com.qzj.dqq.enums.ResultEnum;

/**
 * Created by qzj on 2018/2/25
 */
public class CommonException extends RuntimeException {

    private Integer code;

    public CommonException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public CommonException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public CommonException(String message, Throwable cause) {
        super(message, cause);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
