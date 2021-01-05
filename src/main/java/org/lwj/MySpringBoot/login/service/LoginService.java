package org.lwj.MySpringBoot.login.service;

import org.lwj.MySpringBoot.login.entity.User;

/***
 * 用户登录
 * @author LvWenJin
 *
 */
public interface LoginService {
	
	//获取用户
	User getSubject(String userName);
	
}
