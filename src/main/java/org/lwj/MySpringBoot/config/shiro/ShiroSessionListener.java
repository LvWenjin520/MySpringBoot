package org.lwj.MySpringBoot.config.shiro;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

/***
 * 自定义会话监听器
 * @author LvWenJin
 *
 */
public class ShiroSessionListener implements SessionListener{

	
	/**
     * 统计在线人数
     * juc包下线程安全自增
     */
    private final AtomicInteger sessionCount = new AtomicInteger(0); 
	/**
	 * 在创建session的时候
	 */
	@Override
	public void onStart(Session session) {
		//会话创建在线人数加一
		sessionCount.incrementAndGet();
		System.out.println("session已创建，在线人数："+sessionCount);
	}

	/**
	 * 销毁session的时候
	 */
	@Override
	public void onStop(Session session) {
		sessionCount.decrementAndGet();
		System.out.println("session已销毁，在线人数："+sessionCount+"--"+session.getLastAccessTime()+"--"+session.getStartTimestamp());
	}

	/**
	 * 过期的时候
	 */
	@Override
	public void onExpiration(Session session) {
		//会话过期,在线人数减一
        sessionCount.decrementAndGet();
        System.out.println("session已过期，在线人数："+sessionCount+session.getAttribute("user"));
	}

	/**
	 * 获取在线人数
	 * @return
	 */
	public AtomicInteger getSessionCount() {
		return sessionCount;
	}
	
	
}
