package org.lwj.MySpringBoot.register.service.impl;

import java.util.Map;

import org.apache.dubbo.config.annotation.Reference;
import org.lwj.MySpringBoot.register.service.RegisterService;
import org.springframework.stereotype.Service;

import entitys.user.entity.User;
import services.user.service.UserService;
import utils.jsonmsg.JsonMsg;


@Service  //spring的注解
public class RegisterServiceImpl implements RegisterService{
	//Dubbo的引用注解
	@Reference
	UserService UserService;
	
	@Override
	public Map<String, String> userRegiste(User user) {
		
		int result = UserService.insertUser(user);
		
		if(result == 1) {
			return JsonMsg.success("注册成功");
		}else if(result == 0) {
			return JsonMsg.faild("此用户已存在");
		}
		return JsonMsg.faild("系统错误");
	}

}
