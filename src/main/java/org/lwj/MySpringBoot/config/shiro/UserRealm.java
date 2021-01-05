package org.lwj.MySpringBoot.config.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.lwj.MySpringBoot.login.entity.User;
import org.lwj.MySpringBoot.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义Realm
 * @author user
 *	继承AuthorizingRealm实现登录
 */
public class UserRealm extends AuthorizingRealm{

	
	@Autowired
	LoginService loginService;
	
	//授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		
		//授权
		//info.addStringPermission("user:add");
		
		Subject subject = SecurityUtils.getSubject();
		//在认证方法里最后一行放入的subject对象，在这里可以取出来
		User user = (User)subject.getPrincipal();
		
		//获取当前用户的权限，然后再在请求那里鉴权
		//info.addStringPermission(user.getRoles());
		//info.addRole(user.getRoles());
		info.addRole("root");
		// TODO 自动生成的方法存根
		return info;
	}

	//认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		
		//获取用户
		//Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken userToken = (UsernamePasswordToken)token;
		
		//用户名不存在的情况
		User user = loginService.getSubject(userToken.getUsername());
		
		if(null == user) {
			return null;  //没有此用户
		}
		// 盐值。使用 fuckoff作为盐值，盐值必须是唯一的
        ByteSource credentialsSalt = ByteSource.Util.bytes("fuckoff");
		
		// shiro比对密码的方法，密码由框架处理
		return new SimpleAuthenticationInfo(user,user.getPassWord(),credentialsSalt,getName());
	}

}
