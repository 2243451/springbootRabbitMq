package com.springboot.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Created by Administrator on 2017/7/3.
 */
@Configuration
public class RabbitMQConfig {

    public static final String QUENE_NAME="email_queue";//队列名称
    public static final String  EXCHANGE_EMAIL="ExchangeEmail";//email交换器名称
    public static final String  EXCHANGE_LOG="ExchangeLog";//log交换器名称

    public static final String EMAIL_QUEUE="email.*";
    public static final String EMAIL_QUEUE_163_NAME="email163";
    public static final String EMAIL_QUEUE_QQ_NAME="emailqq";
    public static final String LOG_QUEUE_CONFIG_NAME="config_Log";
    public static final String LOG_QUEUE_SYS_NAME="sys_Log";
    @Bean
    Queue queue() {//队列
//        String name =QUENE_NAME;
//        // 是否持久化
//        boolean durable = true;
//        // 仅创建者可以使用的私有队列，断开后自动删除
//        boolean exclusive = false;
//        // 当所有消费客户端连接断开后，是否自动删除队列
//        boolean autoDelete = false;

        return new Queue(QUENE_NAME);
    }

}
