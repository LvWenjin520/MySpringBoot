package org.lwj.MySpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableAsync  //开启异步执行注解，就是在需要耗时的service的时候，再开一个线程给用户返回信息，后台再处理任务
//程序的主入口，不能删不能改,本身就是spring的组件
@SpringBootApplication
public class MySpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(MySpringBootApplication.class, args);
	}

}
