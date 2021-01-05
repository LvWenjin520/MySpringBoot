package org.lwj.MySpringBoot.mcstudy.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 机器学习控制器
 * @author user
 *
 */
@RestController()
@RequestMapping("/mc")
public class McStudyController {
	
	@RequestMapping(path="/testroot")
	public Map<String,String> testPerms(){
		
		Map<String,String> map = new HashMap<String, String>();
		map.put("msg", "success");
		
		return map;
	}
	
}
