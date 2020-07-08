package com.springboot.service;

/**
 * Created by Administrator on 2017/7/3.
 * 负责发送邮件消息到RabbitMQ
 *
 */

public interface EmailService {

    /**
     * 发送邮件任务存入消息队列
     * @param message
     * @throws Exception
     */
    void sendEmailMsg(String message) throws Exception;


    void RabbitExChangeToTopicMsg(String message);

    void RabbitChangeToFanoutMsg(String message);
}
