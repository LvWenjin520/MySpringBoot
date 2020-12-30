package org.lwj.MySpringBoot.login.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller()
public class LoginController {
	
	@GetMapping("/tologin")
	public String toLogin() {
		return "pages/login";
	}
	
	/**
	 * 认证的方法
	 * @return
	 */
	@PostMapping("/login")
	@ResponseBody
	public Map<String,String> login(
			@RequestParam String username,
			@RequestParam String password){
		
		Map<String,String> result = new HashMap<String, String>();
		
		//获取当前用户
		Subject subject = SecurityUtils.getSubject();
		
		//获取当前传来的用户名密码
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		
		//做登录处理
		try{
			subject.login(token);
			result.put("msg", "success");
			return result;
		}catch(UnknownAccountException e) { //用户名不存在
			result.put("msg", "nouser");
			return result;
		}catch(IncorrectCredentialsException e) { //密码错误
			result.put("msg", "wrongpassword");
			return result;
		}
	}
	
}
