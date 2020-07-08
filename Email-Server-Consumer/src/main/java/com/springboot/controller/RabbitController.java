package com.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.service.EmailService;

/**
 * 
* @Title: RedisController.java
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
	@RequestMapping("/RabbitEmail")
	public String setMessage(String text,String to,String subject) {
		try {
			emailService.sendTextEmail("刘疯子你好", "1195559576@qq.com", "liufengzi");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return to;
		
	}
	
	@RequestMapping("/getRabbitMessage")
	public String getMessage(String message) {
		try {
			emailService.listenerEmailMsg(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
		
	}
}
