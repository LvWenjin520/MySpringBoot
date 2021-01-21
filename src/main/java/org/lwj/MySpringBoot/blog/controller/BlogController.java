package org.lwj.MySpringBoot.blog.controller;

import java.util.Map;

import org.apache.dubbo.config.annotation.Reference;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import services.blog.service.BlogService;

@RestController
@RequestMapping("/blog")
public class BlogController {
	
	@Reference
	BlogService blogService;
	
	@PostMapping("/blog")
	public Map<String,String> insertArticle(
			@RequestParam String articleTitle,
			@RequestParam String article
			){
		Object username = SecurityUtils.getSubject().getSession().getAttribute("user");
		return blogService.insertArticle(username.toString(), articleTitle, article);
	}
	
	
}
