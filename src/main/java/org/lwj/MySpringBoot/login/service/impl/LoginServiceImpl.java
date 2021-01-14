package org.lwj.MySpringBoot.login.service.impl;

import org.apache.dubbo.config.annotation.Reference;
import org.lwj.MySpringBoot.login.service.LoginService;
import org.springframework.stereotype.Service;

import entitys.user.entity.User;
import services.user.service.UserService;

/***
 * 实现类
 * @author LvWenJin
 *
 */
@Service
public class LoginServiceImpl implements LoginService{

	@Reference
	UserService userService;

	@Override
	public User getUser(String userName) {
		
		return userService.getUser(userName);
	}
	
}
