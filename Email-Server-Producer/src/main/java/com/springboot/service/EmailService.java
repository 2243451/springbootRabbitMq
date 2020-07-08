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

    /**
     * @Description: FanOut交换机方式发送至全部队列
     * @Author: lixiaochen
     * @Date: 2020/7/8 14:40
     */
    void RabbitExChangeToTopicMsg(String message);
    /**
     * @Description: 发送至交换机Topic方式发送 可通过RoutingKey匹配
     * @Author: lixiaochen
     * @Date: 2020/7/8 14:40
     */
    void RabbitChangeToFanoutMsg(String message);
}
