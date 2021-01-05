package org.lwj.MySpringBoot.login.controller;

import java.util.Map;

import org.lwj.MySpringBoot.login.entity.User;
import org.lwj.MySpringBoot.login.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
		
		Map<String, String> result = registerService.register(new User(username,password));
		
		return result;
	}
	
}
