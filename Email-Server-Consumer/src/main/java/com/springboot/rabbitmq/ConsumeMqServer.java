package com.springboot.rabbitmq;

import com.rabbitmq.client.Channel;
import com.springboot.bean.Person;
import com.springboot.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class ConsumeMqServer {
    /**
     * @Description: 指定交换机、队列及rouningKey匹配策略
     * @Author: lixiaochen
     * @Date: 2020/7/7 9:40
     */
    @RabbitHandler
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitMQConfig.EMAIL_QUEUE_QQ_NAME,
                    durable="true"),
            exchange = @Exchange(value = RabbitMQConfig.EXCHANGE_EMAIL,
                    durable="true",
                    type= "fanout",
                    ignoreDeclarationExceptions = "true")
    )
    )
    public  void   acceptEmailQQ(String message){
        System.out.println(new Date()+"==QQ消息接收到"+message);
    }
    @RabbitHandler
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitMQConfig.EMAIL_QUEUE_163_NAME,
                    durable="true"),
            exchange = @Exchange(value = RabbitMQConfig.EXCHANGE_EMAIL,
                    durable="true",
                    type= "fanout",
                    ignoreDeclarationExceptions = "true")
    )
    )
    public  void   acceptEmail163(String message){
        System.out.println(new Date() +"====163消息接收到"+message);
    }




    @RabbitHandler
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitMQConfig.LOG_QUEUE_SYS_NAME,
                    durable="true"),
            exchange = @Exchange(value = RabbitMQConfig.EXCHANGE_LOG,
                    durable="true",
                    type= "topic",
                    ignoreDeclarationExceptions = "true"),
                    key = RabbitMQConfig.LOG_QUEUE_SYS_NAME+".*"
    )
    )
    public  void   acceptSysLog(@Payload Person person,
                             Channel channel,
                             @Headers Map<String, Object> headers){
        System.out.println("SystemLog消息接收到"+person.getName());
        System.out.println("SystemLog消息接收到"+channel);
        System.out.println("SystemLog消息接收到"+headers);
    }


    @RabbitHandler
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitMQConfig.LOG_QUEUE_CONFIG_NAME,
                    durable="true"),
            exchange = @Exchange(value = RabbitMQConfig.EXCHANGE_LOG,
                    durable="true",
                    type= "topic",
                    ignoreDeclarationExceptions = "true"),
            key = RabbitMQConfig.LOG_QUEUE_CONFIG_NAME+".*"
    )
    )
    public  void   acceptConfigLog(@Payload Person person,
                             Channel channel,
                             @Headers Map<String, Object> headers){
        System.out.println("SystemLog消息接收到"+person.getName());
        System.out.println("SystemLog消息接收到"+channel);
        System.out.println("SystemLog消息接收到"+headers);
    }


}
