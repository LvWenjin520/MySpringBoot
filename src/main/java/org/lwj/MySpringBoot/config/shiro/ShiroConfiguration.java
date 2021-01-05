package org.lwj.MySpringBoot.config.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 配置shiro
 * @author user
 *
 */
@Configuration
public class ShiroConfiguration {
	
	//配置ShiroFilterFactoryBean
	@Bean
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		//添加shiro过滤器
		/**
		 * anon无需认证
		 * authc必须认证
		 * user必须有记住我功能
		 * perms必须有某个资源的权限才能访问
		 * role必须有某个角色才能访问
		 */
		//设置过滤器
		Map<String, String> filterMap = new LinkedHashMap<String, String>();
		
		//授权控制处理
		filterMap.put("/mc/testroot", "roles[root]");
		//filterMap.put("/mc/testroot", "perms[user:mc]");
		
		//认证过滤器,无需登录的请求
		filterMap.put("/", "anon");
		filterMap.put("/login/**", "anon");
		
		//需要登录的请求
		filterMap.put("/hello", "authc");
		filterMap.put("/mc/**", "authc");
		
		
		//登出请求
		filterMap.put("/logout", "logout");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
		
		//未登录的自动跳转到登录页面
		shiroFilterFactoryBean.setLoginUrl("/login/login");
		
		//没有授权的请求跳转到401页面
		shiroFilterFactoryBean.setUnauthorizedUrl("/401");
		
		//登录成功跳转
		shiroFilterFactoryBean.setSuccessUrl("/hello");
		
		
		return shiroFilterFactoryBean;
	}
	
	
	
	
	//生成WebSecurityManager                                       绑定下面的realm
	@Bean(name="securityManager")
	public DefaultWebSecurityManager getDefaultWebSecurityManager(
			@Qualifier("userRealm") UserRealm userRealm,
			@Qualifier("sessionManager") DefaultWebSessionManager sessionManager) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setSessionManager(sessionManager);
		securityManager.setRealm(userRealm);
		return securityManager;
	}
	
	
	/**
	 * 处理session
	 * @return
	 */
	@Bean("sessionManager")
	public DefaultWebSessionManager getDefaultWebSessionManager() {
		DefaultWebSessionManager manager = new DefaultWebSessionManager();
		//解决重定向后的自动添加JSessionId的问题
		manager.setSessionIdUrlRewritingEnabled(false);
		return manager;
	}
	
	@Bean("hashedCredentialsMatcher")
	public HashedCredentialsMatcher getHashedCredentialsMatcher() {
		//加密
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
		// 使用md5 算法进行加密
		matcher.setHashAlgorithmName("MD5");
        // 设置散列次数： 意为加密几次
		matcher.setHashIterations(1024);
		return matcher;
	}
	
	
	//注入配置好的userRealm，交给spring托管
	@Bean(name="userRealm")
	public UserRealm userRealm(
			@Qualifier("hashedCredentialsMatcher") HashedCredentialsMatcher matcher
			) {
		
		UserRealm userRealm = new UserRealm();
		userRealm.setCredentialsMatcher(matcher);
		return userRealm;
	}
}
