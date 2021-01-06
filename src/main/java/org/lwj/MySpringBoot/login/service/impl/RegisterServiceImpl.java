package org.lwj.MySpringBoot.login.service.impl;

import java.util.Map;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.lwj.MySpringBoot.login.dao.RegisterDao;
import org.lwj.MySpringBoot.login.dao.UserDao;
import org.lwj.MySpringBoot.login.entity.User;
import org.lwj.MySpringBoot.login.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utils.jsonmsg.JsonMsg;


@Service()
public class RegisterServiceImpl implements RegisterService{

	@Autowired
	RegisterDao registerDao;
	
	@Autowired
	UserDao userDao;
	
	//@Async //此任务为异步任务
	@Override
	public Map<String, String> register(User user) {
		//加密
		Md5Hash md5 = new Md5Hash(user.getPassWord(),"fuckoff",1024);
		//加密后的密码
		String password = md5.toHex();
		//更新加密后的密码
		user.setPassWord(password);
		
		int userNum = userDao.queryUserNumByUserName(user.getUserName());
		
		int insertFlag = 0;
		if(userNum == 0) {
			insertFlag = registerDao.insertUser(user);
			if(insertFlag == 1) {
				return JsonMsg.success("注册成功");
			}
		}else if(userNum == 1) {
			return JsonMsg.faild("用户名已存在");
		}
		return JsonMsg.faild("系统错误");
	}
	
	
}
