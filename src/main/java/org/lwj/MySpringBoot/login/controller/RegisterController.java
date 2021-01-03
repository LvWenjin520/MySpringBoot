package org.lwj.MySpringBoot.login.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import utils.jsonmsg.JsonMsg;

@RestController("/register")
public class RegisterController {
	
	@RequestMapping("/register")
	public Map<String,String> register(
			@RequestParam String username,
			@RequestParam String password
			) {
		
		return JsonMsg.success("register Success");
	}
	
}
