package com.springboot.service;

/**
 * Created by Administrator on 2017/7/3.
 * 负责发送邮件消息到RabbitMQ
 *
 */

public interface EmailService {



    /**
     * 发送纯文本邮件
     * @param text 邮件文本
     * @param to 接收人的邮箱
     * @param subject 邮件主题
     */
    void  sendTextEmail(String text,String to,String subject);

    /**
     * 发送网页邮件
     * @param html 网页
     * @param to 接收人的邮箱
     * @param subject 邮件主题
     */
    void  sendHtmlEmail(String html,String to,String subject);

    /**
     * 发送附件的邮件
     * @param to 接收人的邮箱
     * @param subject 邮件主题
     * @param subject 邮件主题
     * @param files 附件的文件地址 c:xxx.jpg
     */
    void sendAttachmentsMail(String to,String subject,String text,String ... files);

    /**
     * 接收rabbitmq的邮件消息
     * @param message
     */
    void listenerEmailMsg(String message);
}
