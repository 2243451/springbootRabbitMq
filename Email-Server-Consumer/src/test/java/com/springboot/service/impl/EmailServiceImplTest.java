package com.springboot.service.impl;

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

    }

    @Test
    public void receiveEmailMsg() throws Exception {

    }

}