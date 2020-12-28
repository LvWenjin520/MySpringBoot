package org.lwj.MySpringBoot.mcstudy.controller;

import org.lwj.MySpringBoot.mcstudy.dao.McStudyMapper;
import org.lwj.MySpringBoot.mcstudy.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 机器学习控制器
 * @author user
 *
 */
@RestController
public class McStudyController {
	
	@Autowired
	McStudyMapper mcStudyMapper;
	
	@GetMapping("query")
	public void queryTest() {
		User user = mcStudyMapper.query(1);
		System.out.println(user.getId());
	}
	
	
}
