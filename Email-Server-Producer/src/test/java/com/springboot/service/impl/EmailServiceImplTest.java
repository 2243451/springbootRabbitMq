package com.springboot.service.impl;

import com.alibaba.fastjson.JSON;
import com.springboot.bean.Email;
import com.springboot.service.EmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Administrator on 2017/7/3.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailServiceImplTest {

    @Autowired
    EmailService emailService;

    @Test
    public void sendEmailMsg() throws Exception {
        Email email=new Email("邮件内容123","xxxx@163.com","邮件主题");
        emailService.sendEmailMsg(JSON.toJSONString(email));

    }

}