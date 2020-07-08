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
	@RequestMapping("/Rabbit")
	public String setMessage(String message) {
		try {
			emailService.sendEmailMsg(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ok";
		
	}
    @GetMapping("/RabbitChangeToFanoutMsg")
    public String RabbitChangeToFanoutMsg(String message) {
        try {
            emailService.RabbitChangeToFanoutMsg(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ok";

    }
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
