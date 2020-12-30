package org.lwj.MySpringBoot.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

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
		
		//获取用户
		Subject subject = SecurityUtils.getSubject();
		
		
		// TODO 自动生成的方法存根
		return null;
	}

}
