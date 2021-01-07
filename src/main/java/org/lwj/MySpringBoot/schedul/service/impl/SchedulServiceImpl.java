package org.lwj.MySpringBoot.schedul.service.impl;

import org.lwj.MySpringBoot.schedul.service.SchedulService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/***
 * 不需要调用，直接运行
 * @author user
 *
 */
@Service
public class SchedulServiceImpl implements SchedulService {

	//cron表达式放在括号里
	//秒  分  时  日  月  周
	//"0 0 0 23 * ?" 每天夜里12点
	@Scheduled(cron="0 0 23 * * ?")  //每天的每分钟的第0秒执行，可以上网查cron来网上自动来做
	public void hello() {
		// TODO 自动生成的方法存根
		System.out.println("hello");
	}

}
