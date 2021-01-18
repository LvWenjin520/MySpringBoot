package org.lwj.MySpringBoot.login.service.impl;

import org.apache.dubbo.config.annotation.Reference;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.lwj.MySpringBoot.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	SessionDAO sessionDao;
	
	@Override
	public User getUser(String userName) {
		
		return userService.getUser(userName);
	}

	@Override
	public boolean removeAllUser() {
		
		return false;
	}
	
}
