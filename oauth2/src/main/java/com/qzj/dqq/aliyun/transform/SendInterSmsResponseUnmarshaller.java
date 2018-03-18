package com.qzj.dqq.aliyun.transform;

/**
 * Created by qzj on 2018/1/12
 */

import com.aliyuncs.transform.UnmarshallerContext;
import com.qzj.dqq.aliyun.model.SendInterSmsResponse;

public class SendInterSmsResponseUnmarshaller {
    public SendInterSmsResponseUnmarshaller() {
    }

    public static SendInterSmsResponse unmarshall(SendInterSmsResponse sendInterSmsResponse, UnmarshallerContext context) {
        sendInterSmsResponse.setRequestId(context.stringValue("SendInterSmsResponse.RequestId"));
        sendInterSmsResponse.setBizId(context.stringValue("SendInterSmsResponse.BizId"));
        sendInterSmsResponse.setCode(context.stringValue("SendInterSmsResponse.Code"));
        sendInterSmsResponse.setMessage(context.stringValue("SendInterSmsResponse.Message"));
        return sendInterSmsResponse;
    }
}
