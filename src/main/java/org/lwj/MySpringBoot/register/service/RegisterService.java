package org.lwj.MySpringBoot.register.service;

import java.util.Map;

import entitys.user.entity.User;

/***
 * 用户注册的服务
 * @author user
 *
 */
public interface RegisterService {
	Map<String,String> userRegiste(User user);
}
