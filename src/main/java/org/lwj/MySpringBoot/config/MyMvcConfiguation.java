package org.lwj.MySpringBoot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//加上注解实现接口可以扩展
@Configuration
public class MyMvcConfiguation implements WebMvcConfigurer{
	
	
	//视图跳转控制器
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/lwj").setViewName("test01");
	}
	
}
