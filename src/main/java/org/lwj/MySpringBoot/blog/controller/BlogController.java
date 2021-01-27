package org.lwj.MySpringBoot.blog.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.dubbo.config.annotation.Reference;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import entitys.blog.entity.Article;
import services.blog.service.BlogService;

@RestController
@RequestMapping("/blog")
public class BlogController {
	
	@Reference
	BlogService blogService;
	
	@GetMapping("/blog")
	public List<Article> getArticle(HttpSession session){
		Object attribute = session.getAttribute("user");
		String userName = attribute.toString();
		return blogService.getArticle(userName);
	}
	
	//删除文章
	@RequestMapping(value="/blog/{articleId}",method=RequestMethod.DELETE)
	public Map<String,String> deleteArticle(
			@PathVariable("articleId") int articleId 
			){
		return blogService.deleteArticle(articleId);
	}
	
	@PostMapping("/blog")
	public Map<String,String> insertArticle(
			@RequestParam String articleTitle,
			@RequestParam String article
			){
		Object username = SecurityUtils.getSubject().getSession().getAttribute("user");
		return blogService.insertArticle(username.toString(), articleTitle, article);
	}
	
	
}
