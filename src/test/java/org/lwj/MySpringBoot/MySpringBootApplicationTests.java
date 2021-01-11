package org.lwj.MySpringBoot;



import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.dubbo.config.annotation.Reference;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import test.service.TestService;

@SpringBootTest
class MySpringBootApplicationTests {

	
	@Autowired
	JavaMailSenderImpl javaMail;
	
	@Reference
	TestService testService;
	
	@Test
	void testDubbo() {
		String test = testService.test();
		System.out.println(test);
	}
	
	
	//发送简单的邮件
	//@Test
	void contextLoads(){
		SimpleMailMessage simpleMessage = new SimpleMailMessage();
		//标题
		simpleMessage.setSubject("我是主题");
		//正文
		simpleMessage.setText("hello world");
		//发给谁
		simpleMessage.setTo("645991686@qq.com");
		//从哪儿接受
		simpleMessage.setFrom("972950811@qq.com");
		javaMail.send(simpleMessage);
	}
	
	//发送复杂的邮件
	//@Test
	void contextLoads2() throws MessagingException{
		//复杂的邮件
		MimeMessage createMimeMessage = javaMail.createMimeMessage();
		//组装,true是可以解析html
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(createMimeMessage,true);
		
		mimeMessageHelper.setSubject("你好，我是主题");
		mimeMessageHelper.setText("<a href='#'>点我</a>", true);
		mimeMessageHelper.addAttachment("文档.txt",new File("D:\\Git\\project\\MySpringBoot\\src\\main\\resources\\docs\\文档.txt") );
		
		mimeMessageHelper.setTo("645991686@qq.com");
		mimeMessageHelper.setFrom("972950811@qq.com");
		javaMail.send(createMimeMessage);
	}
	

}
