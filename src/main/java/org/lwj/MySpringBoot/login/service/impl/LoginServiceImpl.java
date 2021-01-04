package org.lwj.MySpringBoot.login.service.impl;

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
	
}
