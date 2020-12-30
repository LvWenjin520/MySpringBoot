package org.lwj.MySpringBoot.config;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * 自定义Realm
 * @author user
 *	继承AuthorizingRealm实现登录
 */
public class UserRealm extends AuthorizingRealm{

	//授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO 自动生成的方法存根
		return null;
	}

	//认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("执行了认证");
		//获取用户
		//Subject subject = SecurityUtils.getSubject();
		//在这里从数据库获取用户名密码
		String username="lwj";
		String password="123456";
		
		
		UsernamePasswordToken userToken = (UsernamePasswordToken)token;
		
		//用户名不存在的情况
		if(!userToken.getUsername().equals(username)) {
			return null;
		}
		
		// shiro比对密码的方法，密码由框架处理
		return new SimpleAuthenticationInfo("",password,"");
	}

}
