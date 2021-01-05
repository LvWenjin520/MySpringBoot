package org.lwj.MySpringBoot.config.shiro;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
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
		//配置会话管理器
		securityManager.setSessionManager(sessionManager);
		
		 //配置 ehcache缓存管理器 参考博客：
	    securityManager.setCacheManager(getEhCacheManager());
		securityManager.setRealm(userRealm);
		return securityManager;
	}
	
	
	/**
	 * 处理session
	 * @return
	 */
	@Bean("sessionManager")
	public DefaultWebSessionManager getDefaultWebSessionManager(
			@Qualifier("sessionDAO") SessionDAO sessionDao
			) {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
	    Collection<SessionListener> listeners = new ArrayList<SessionListener>();
	    //配置监听
	    listeners.add(getSessionListener());
	    sessionManager.setSessionListeners(listeners);
	    sessionManager.setSessionIdCookie(sessionIdCookie());
	    sessionManager.setSessionDAO(sessionDAO());
	    sessionManager.setCacheManager(getEhCacheManager());

	    //全局会话超时时间（单位毫秒），默认30分钟  暂时设置为10秒钟 用来测试
	    //sessionManager.setGlobalSessionTimeout(10000);
	    //是否开启删除无效的session对象  默认为true
	    sessionManager.setDeleteInvalidSessions(true);
	    //是否开启定时调度器进行检测过期session 默认为true
	    sessionManager.setSessionValidationSchedulerEnabled(true);
	    //设置session失效的扫描时间, 清理用户直接关闭浏览器造成的孤立会话 默认为 1个小时
	    //设置该属性 就不需要设置 ExecutorServiceSessionValidationScheduler 底层也是默认自动调用ExecutorServiceSessionValidationScheduler
	    //暂时设置为 5秒 用来测试
	    //20分钟扫描一次，清理失效的session
	    sessionManager.setSessionValidationInterval(1000*60*20);

	    //取消url 后面的 JSESSIONID
	    sessionManager.setSessionIdUrlRewritingEnabled(false);

	    return sessionManager;
	}
	
	
	/**
	 * 配置会话监听器
	 * @return
	 */
	@Bean("sessionListener")
	public SessionListener getSessionListener() {
		ShiroSessionListener sessionListener = new ShiroSessionListener();
	    return sessionListener;
	}
	
	/**
	 * 配置会话ID生成器
	 * @return
	 */
	@Bean
	public SessionIdGenerator sessionIdGenerator() {
	    return new JavaUuidSessionIdGenerator();
	}
	/**
	 * SessionDAO的作用是为Session提供CRUD并进行持久化的一个shiro组件
	 * MemorySessionDAO 直接在内存中进行会话维护
	 * EnterpriseCacheSessionDAO  提供了缓存功能的会话维护，默认情况下使用MapCache实现，内部使用ConcurrentHashMap保存缓存的会话。
	 * @return
	 */
	@Bean("sessionDAO")
	public SessionDAO sessionDAO() {
	    EnterpriseCacheSessionDAO enterpriseCacheSessionDAO = new EnterpriseCacheSessionDAO();
	    //使用ehCacheManager
	    enterpriseCacheSessionDAO.setCacheManager(getEhCacheManager());
	    //设置session缓存的名字 默认为 shiro-activeSessionCache
	    enterpriseCacheSessionDAO.setActiveSessionsCacheName("shiro-activeSessionCache");
	    //sessionId生成器
	    enterpriseCacheSessionDAO.setSessionIdGenerator(sessionIdGenerator());
	    return enterpriseCacheSessionDAO;
	}
	
	
	/**
	 * 配置保存sessionId的cookie 
	 * 注意：这里的cookie 不是上面的记住我 cookie 记住我需要一个cookie session管理 也需要自己的cookie
	 * @return
	 */
	@Bean("sessionIdCookie")
	public SimpleCookie sessionIdCookie(){
	    //这个参数是cookie的名称
	    SimpleCookie simpleCookie = new SimpleCookie("sid");
	    //setcookie的httponly属性如果设为true的话，会增加对xss防护的安全系数。它有以下特点：

	    //setcookie()的第七个参数
	    //设为true后，只能通过http访问，javascript无法访问
	    //防止xss读取cookie
	    simpleCookie.setHttpOnly(true);
	    simpleCookie.setPath("/");
	    //maxAge=-1表示浏览器关闭时失效此Cookie
	    simpleCookie.setMaxAge(-1);
	    return simpleCookie;
	}
	
	
	
	
	/**
	 * 缓存管理器
	 *
	 * @return ehCacheManager
	 */
	@Bean("ehCacheManager")
	public EhCacheManager getEhCacheManager() {
	    EhCacheManager ehCacheManager = new EhCacheManager();
	    ehCacheManager.setCacheManagerConfigFile("classpath:org/lwj/MySpringBoot/config/shiro/shiro-ehcache.xml");
	    return ehCacheManager;
	}
	
	
	
	
	//密码加密匹配器
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
			@Qualifier("hashedCredentialsMatcher") HashedCredentialsMatcher matcher,
			@Qualifier("ehCacheManager") EhCacheManager ehCacheManager
			) {
		
		UserRealm userRealm = new UserRealm();
		userRealm.setCredentialsMatcher(matcher);
		//启用缓存
		userRealm.setCachingEnabled(true);
		//设置认证缓存名
		userRealm.setAuthenticationCacheName("authenticationCache");
		//设置授权缓存名
		userRealm.setAuthorizationCacheName("authorizationCache");
		
		//配置自定义缓存
		userRealm.setCacheManager(ehCacheManager);
		return userRealm;
	}
}
