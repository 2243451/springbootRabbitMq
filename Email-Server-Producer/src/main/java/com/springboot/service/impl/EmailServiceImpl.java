package com.springboot.service.impl;

import com.springboot.bean.Person;
import com.springboot.config.RabbitMQConfig;
import com.springboot.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by Administrator on 2017/7/3.
 */
@Service
public class EmailServiceImpl implements EmailService,RabbitTemplate.ConfirmCallback{
    private static Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);
    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate1;


    @PostConstruct
    public void init(){
        //指定 ConfirmCallback
        this.rabbitTemplate1.setConfirmCallback(this);
    }

    /**
     * 发送邮件任务存入消息队列
     *
     * @param message
     * @throws Exception
     */
    @Override
    public void sendEmailMsg(String message) throws Exception {
        logger.info("邮件发送成功");
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE,RabbitMQConfig.ROUTEKEY,message);

    }

    @Override
    public void RabbitExChangeToTopicMsg(String message) {
        Person person = new Person();
        person.setId(111L);
        person.setAge("13");
        person.setName("SAM:"+message);
        //参数说明（交换机，路由key，消息实体类，消息确认唯一ID）
        CorrelationData correlationData = new CorrelationData(person.getId().toString());
        rabbitTemplate1.convertAndSend(RabbitMQConfig.EXCHANGE_LOG,"sys_Log.lxc",person,correlationData);
    }
    /**
     * @Description: 直接发送数据到对应交换机（关联该交换机的用于消费）
     * @Author: lixiaochen
     * @Date: 2020/7/7 13:54
     */
    @Override
    public void RabbitChangeToFanoutMsg(String message) {

        rabbitTemplate1.convertAndSend(RabbitMQConfig.EXCHANGE_EMAIL,"",message);
    }


    /**
     * 消息确认消费
     * @param correlationData
     * @param ack
     * @param cause
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println("confirm--:回调方法:"+correlationData+",ack:"+ack+",cause:"+cause);
    }
}
