package org.lwj.MySpringBoot.login.service.impl;

import java.util.Map;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.lwj.MySpringBoot.login.dao.RegisterDao;
import org.lwj.MySpringBoot.login.entity.User;
import org.lwj.MySpringBoot.login.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utils.jsonmsg.JsonMsg;


@Service("register")
public class RegisterServiceImpl implements RegisterService{

	@Autowired
	RegisterDao registerDao;
	
	@Override
	public Map<String, String> register(User user) {
		//加密
		Md5Hash md5 = new Md5Hash(user.getPassWord(),"fuckoff",1024);
		//加密后的密码
		String password = md5.toHex();
		//更新加密后的密码
		user.setPassWord(password);
		
		//插入用户信息
		boolean insertUser = registerDao.insertUser(user);
		
		if(insertUser) {
			return JsonMsg.success("注册成功");
		}
		
		return JsonMsg.faild("注册失败");
	}
	
	
}
