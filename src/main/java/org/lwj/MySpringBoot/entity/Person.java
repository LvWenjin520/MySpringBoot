package org.lwj.MySpringBoot.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component()
@ConfigurationProperties(prefix = "myperson")
//@Validated  //校验的注解
public class Person {
	
	//@Value("12")
	private int age;
	//@Value("aaa")
	//@Email(message="格式错误")
	private String name;
	//@Value("true")
	private Boolean sex;
	public Person(int age, String name, Boolean sex) {
		this.age = age;
		this.name = name;
		this.sex = sex;
	}
	
	public Person() {
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getSex() {
		return sex;
	}

	public void setSex(Boolean sex) {
		this.sex = sex;
	}
	
	@Override
	public String toString() {
		// TODO 自动生成的方法存根
		return this.name+this.age+this.sex;
	}
	
	
}
