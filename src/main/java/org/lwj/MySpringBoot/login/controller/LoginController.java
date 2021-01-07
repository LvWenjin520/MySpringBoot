package org.lwj.MySpringBoot.login.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import utils.jsonmsg.JsonMsg;

@Controller()
@RequestMapping("/login")
public class LoginController {
	
	Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
	
	
	@RequestMapping(path="/login",method=RequestMethod.GET)
	public String toLogin() {
		logger.info("登录");
		return "pages/login";
	}
	
	/**
	 * 认证的方法
	 * @return
	 */
	@RequestMapping(path="/login",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> login(
			HttpServletRequest request,
			@RequestParam String username,
			@RequestParam String password){
		//获取当前用户
		Subject subject = SecurityUtils.getSubject();
		//获取当前传来的用户名密码
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		
		//如果已经登录过了
		if(subject.isAuthenticated()) {
			return JsonMsg.success("authenticated");
		}
		
		//做登录处理
		try{
			subject.login(token);
			HttpSession session = request.getSession();
			//在会话中存入用户
			session.setAttribute("user",username);
			return JsonMsg.success("success");
		}catch(UnknownAccountException e) { //用户名不存在
			return JsonMsg.faild("no user");
		}catch(IncorrectCredentialsException e) { //密码错误
			return JsonMsg.faild("woring password");
		}
	}
	
}
