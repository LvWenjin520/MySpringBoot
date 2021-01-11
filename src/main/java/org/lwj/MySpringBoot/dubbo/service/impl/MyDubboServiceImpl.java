package org.lwj.MySpringBoot.dubbo.service.impl;

import org.apache.dubbo.config.annotation.Reference;
import org.lwj.MySpringBoot.dubbo.service.MyDubboService;
import org.springframework.stereotype.Service;

import test.service.TestService;

@Service
public class MyDubboServiceImpl implements MyDubboService{

	@Reference
	TestService testService;
	
	@Override
	public String test01() {
		// TODO Auto-generated method stub
		return testService.test();
	}
	
}
