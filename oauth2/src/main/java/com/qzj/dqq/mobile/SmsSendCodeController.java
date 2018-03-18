package com.qzj.dqq.mobile;

import com.aliyuncs.exceptions.ClientException;
import com.qzj.dqq.aliyun.AliyunSMS;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by qzj on 2018/2/26
 */
@RestController
public class SmsSendCodeController {

    @GetMapping("/code/sms")
    public String sendSms(String phone){
        String status = "";
        try {
            status = AliyunSMS.sendSms(phone);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return status;
    }
}
