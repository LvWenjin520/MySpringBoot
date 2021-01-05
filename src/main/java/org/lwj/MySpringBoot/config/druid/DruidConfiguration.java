package org.lwj.MySpringBoot.config.druid;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;

@Configuration
public class DruidConfiguration {
	
	@ConfigurationProperties(prefix="spring.datasource")
	@Bean
	public DataSource druidDataSource() {
		return new DruidDataSource();
	}
	
	/**
	 * 后台监控,固定写法
	 */
	@Bean
	public ServletRegistrationBean<StatViewServlet> statViewServlet(){
		ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(),"/druid/*");
		
		//配置账号密码
		//设置初始化参数
		HashMap<String, String> initParameters = new HashMap<String,String>();
		initParameters.put("loginUsername", "admin");
		initParameters.put("loginPassword", "123456");
		//允许所有人访问
		initParameters.put("allow", "");
		
		bean.setInitParameters(initParameters);
		
		return bean;
	}
	
}
