package com.jason.test.project.controller;

import com.jason.base.exception.ServiceException;
import com.jason.base.utils.MailUtil;
import com.jason.test.common.bean.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

/**
 * @author WongChenHoll
 * @description
 * @date 2023-2-22 星期三 13:15
 **/
@RestController
@RequestMapping("/email")
public class EmailController {

    @GetMapping("/send/qq/{authorizationCode}")
    public BaseResponse<Object> sendqq(@PathVariable String authorizationCode) {
        MailUtil mail = new MailUtil("1320194169", authorizationCode);
        mail.senderAddress("1320194169@qq.com");
        mail.receiverAddress("whx20100101@163.com");
//        mail.ccAddress("");
        mail.emailTitle("test javax.mail send");
        mail.emailContent("qq发送");
        mail.username("JASON");
        File file = new File("C:\\Users\\WangChenHol\\Desktop\\input_product.csv");
        mail.attachment(file);
        try {
            mail.sendQQEmail();
        } catch (ServiceException e) {
            return BaseResponse.error(e);
        }
        return BaseResponse.success("");
    }

    @GetMapping("/send/163/{authorizationCode}")
    public BaseResponse<Object> send163(@PathVariable String authorizationCode) {
        MailUtil mail = new MailUtil("whx20100101@163.com", authorizationCode);
        mail.senderAddress("whx20100101@163.com");
        mail.receiverAddress("1320194169@qq.com");
//        mail.ccAddress("");
        mail.emailTitle("test javax.mail send from 163");
        mail.emailContent("163发送");
        mail.username("JASON");
        File file = new File("C:\\Users\\WangChenHol\\Desktop\\input_product.csv");
        mail.attachment(file);
        try {
            mail.send163Email();
        } catch (ServiceException e) {
            return BaseResponse.error(e);
        }
        return BaseResponse.success("");
    }
}
