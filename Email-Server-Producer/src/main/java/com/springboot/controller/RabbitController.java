package com.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.service.EmailService;

/**
 * 
* @Title: RabbitController.java
* @Package com.tocel.framework.cache
* @Description: TODO(用一句话描述该文件做什么)
* @author 李晓晨
* @date 2018年9月27日
* @version V3.0
 */
@Controller
public class RabbitController {
	@Autowired
	private  EmailService emailService;
	/**
	 * @Description: 发送消息至mq队列
	 * @Author: lixiaochen
	 * @Date: 2020/7/8 14:39
	 */
	@RequestMapping("/Rabbit")
	public String setMessage(String message) {
		try {
			emailService.sendEmailMsg(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ok";
		
	}
	/**
	 * @Description: FanOut交换机方式发送至全部队列
	 * @Author: lixiaochen
	 * @Date: 2020/7/8 14:40
	 */
    @GetMapping("/RabbitChangeToFanoutMsg")
    public String RabbitChangeToFanoutMsg(String message) {
        try {
            emailService.RabbitChangeToFanoutMsg(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ok";

    }
    /**
     * @Description: 发送至交换机Topic方式发送 可通过RoutingKey匹配
     * @Author: lixiaochen
     * @Date: 2020/7/8 14:40
     */
    @GetMapping("/RabbitExChangeToTopicMsg")
    public String RabbitExChangeToTopicMsg(String message) {
        try {
            emailService.RabbitExChangeToTopicMsg(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ok";

    }
}
