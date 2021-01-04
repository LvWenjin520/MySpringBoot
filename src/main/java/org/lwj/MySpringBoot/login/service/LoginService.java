package org.lwj.MySpringBoot.login.service;

import org.lwj.MySpringBoot.login.entity.User;

/***
 * 用户登录
 * @author LvWenJin
 *
 */
public interface LoginService {
	
	User getSubject(String userName);
	
}
