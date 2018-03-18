package com.qzj.dqq.aliyun.model;

/**
 * Created by qzj on 2018/1/12
 */

import com.aliyuncs.AcsResponse;
import com.aliyuncs.transform.UnmarshallerContext;
import com.qzj.dqq.aliyun.transform.SendInterSmsResponseUnmarshaller;

public class SendInterSmsResponse extends AcsResponse {
    private String requestId;
    private String bizId;
    private String code;
    private String message;

    public SendInterSmsResponse() {
    }

    public String getRequestId() {
        return this.requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getBizId() {
        return this.bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SendInterSmsResponse getInstance(UnmarshallerContext context) {
        return SendInterSmsResponseUnmarshaller.unmarshall(this, context);
    }
}
