package org.lwj.MySpringBoot.login.service;

import entitys.user.entity.User;

/***
 * 用户登录
 * @author LvWenJin
 *
 */
public interface LoginService {
	
	//获取用户
	User getUser(String userName);
	
}
