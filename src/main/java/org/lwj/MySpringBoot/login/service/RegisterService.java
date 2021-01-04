package org.lwj.MySpringBoot.login.service;

import java.util.Map;

import org.lwj.MySpringBoot.login.entity.User;

/**
 * 注册的接口
 * @author user
 *
 */
public interface RegisterService {
	
	Map<String,String> register(User user);
}
