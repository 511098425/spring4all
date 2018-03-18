package com.qzj.dqq.aliyun.transform;

/**
 * Created by qzj on 2018/1/12
 */
import com.aliyuncs.transform.UnmarshallerContext;
import com.qzj.dqq.aliyun.model.SendSmsResponse;

public class SendSmsResponseUnmarshaller {
    public SendSmsResponseUnmarshaller() {
    }

    public static SendSmsResponse unmarshall(SendSmsResponse sendSmsResponse, UnmarshallerContext context) {
        sendSmsResponse.setRequestId(context.stringValue("SendSmsResponse.RequestId"));
        sendSmsResponse.setBizId(context.stringValue("SendSmsResponse.BizId"));
        sendSmsResponse.setCode(context.stringValue("SendSmsResponse.Code"));
        sendSmsResponse.setMessage(context.stringValue("SendSmsResponse.Message"));
        return sendSmsResponse;
    }
}