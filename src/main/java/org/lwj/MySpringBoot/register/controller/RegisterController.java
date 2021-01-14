package org.lwj.MySpringBoot.register.controller;

import java.util.Map;

import org.lwj.MySpringBoot.register.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import entitys.user.entity.User;

@RestController()
@RequestMapping("/register")
public class RegisterController {
	
	@Autowired
	RegisterService registerService;
	
	@RequestMapping(path="/regist",method=RequestMethod.POST)
	public Map<String,String> register(
			@RequestParam String username,
			@RequestParam String password
			) {
		User user = new User(username,password);
		return registerService.userRegiste(user);
	}
	
}
