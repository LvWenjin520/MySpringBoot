package org.lwj.MySpringBoot.login.service.impl;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.lwj.MySpringBoot.login.dao.LoginDao;
import org.lwj.MySpringBoot.login.entity.User;
import org.lwj.MySpringBoot.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/***
 * 实现类
 * @author LvWenJin
 *
 */
@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	LoginDao loginDao;
	
	@Override
	public User getSubject(String userName) {
		User subject = loginDao.getSubject(userName);
		return subject;
	}

	/**
	 * 	注册用户，使用盐值加密
	 */
	@Override
	public boolean register(User user) {
		//加密
		Md5Hash md5 = new Md5Hash(user.getPassWord(),"fuckoff",1024);
		//加密后的密码
		String password = md5.toHex();
		return false;
	}
	
}
