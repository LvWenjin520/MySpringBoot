package org.lwj.MySpringBoot.config;

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
		return new UserRealm();
	}
}
