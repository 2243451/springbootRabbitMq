package com.springboot.service.impl;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import com.springboot.bean.Email;
import com.springboot.config.RabbitMQConfig;
import com.springboot.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * Created by Administrator on 2017/7/3.
 */
@Service
@RabbitListener(queues = RabbitMQConfig.QUENE_NAME)
public class EmailServiceImpl implements EmailService{
    private static Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    MailProperties mailProperties;

    /**
     * 发送纯文本邮件
     *
     * @param text    邮件文本
     * @param to      接收人的邮箱
     * @param subject 邮件主题
     */
    @Override
    public void sendTextEmail(String text, String to, String subject) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("主题：简单邮件");
        simpleMailMessage.setText(text);
        simpleMailMessage.setFrom(mailProperties.getUsername());
        simpleMailMessage.setTo(to);

        javaMailSender.send(simpleMailMessage);
    }

    /**
     * 发送网页邮件
     *
     * @param html    网页
     * @param to      接收人的邮箱
     * @param subject 邮件主题
     */
    @Override
    public void sendHtmlEmail(String html, String to, String subject) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(mailProperties.getUsername());
            helper.setTo(to);
            helper.setSubject(subject);
            // "<html><body><img src='http://baidu.com/2/1.jpg' ></body></html>"
            helper.setText(html, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }


        javaMailSender.send(mimeMessage);
    }

    /**
     * 发送附件的邮件
     *
     * @param to      接收人的邮箱
     * @param subject 邮件主题
     * @param text 邮件文本
     * @param files   附件的文件地址 c:xxx.jpg
     */
    @Override
    public void sendAttachmentsMail(String to, String subject,String text, String... files) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(mailProperties.getUsername());
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);


            if (files!=null){
                for (String f:files){
                    FileSystemResource file = new FileSystemResource(new File(f));
                    helper.addAttachment("附件-1.jpg", file);
                    helper.addAttachment("附件-2.jpg", file);
                }
            }
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        } finally {
        }
    }



    /**
     * 接收rabbitmq的邮件消息
     *
     * @param message
     */
    @Override
    @RabbitHandler
    public void listenerEmailMsg(String message) {
        try {
            logger.info("接收到RabbitMQ的消息："+message);
            sendTextEmail(message,"xiaochen@163.com","致李晓晨的一封信");
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
