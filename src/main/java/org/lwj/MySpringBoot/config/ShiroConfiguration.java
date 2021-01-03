package org.lwj.MySpringBoot.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
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
		
		//认证过滤器
		filterMap.put("/", "anon");
		filterMap.put("/mc", "authc");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
		
		//未登录的自动跳转到登录页面
		shiroFilterFactoryBean.setLoginUrl("tologin");
		
		//没有授权的请求跳转到401页面
		shiroFilterFactoryBean.setUnauthorizedUrl("/401");
		
		return shiroFilterFactoryBean;
	}
	
	//生成WebSecurityManager                                       绑定下面的realm
	@Bean(name="securityManager")
	public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(userRealm);
		return securityManager;
	}
	//注入配置好的userRealm，交给spring托管
	@Bean(name="userRealm")
	public UserRealm userRealm() {
		
		UserRealm userRealm = new UserRealm();
		
		//加密
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
		// 使用md5 算法进行加密
		matcher.setHashAlgorithmName("md5");
        // 设置散列次数： 意为加密几次
		matcher.setHashIterations(1024);
		
		userRealm.setCredentialsMatcher(matcher);
		return userRealm;
	}
}
