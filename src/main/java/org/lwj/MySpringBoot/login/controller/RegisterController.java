package org.lwj.MySpringBoot.login.controller;

import java.util.Map;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import entitys.user.entity.User;
import services.user.service.UserService;
import utils.jsonmsg.JsonMsg;

@RestController()
@RequestMapping("/register")
public class RegisterController {
	
	//Dubbo的引用注解
	@Reference
	UserService UserService;
	
	@RequestMapping(path="/regist",method=RequestMethod.POST)
	public Map<String,String> register(
			@RequestParam String username,
			@RequestParam String password
			) {
		
		User user = new User(username,password);
		int result = UserService.insertUser(user);
		
		if(result == 1) {
			return JsonMsg.success("注册成功");
		}else if(result == 0) {
			return JsonMsg.faild("此用户已存在");
		}
		
		return JsonMsg.faild("系统错误");
	}
	
}
