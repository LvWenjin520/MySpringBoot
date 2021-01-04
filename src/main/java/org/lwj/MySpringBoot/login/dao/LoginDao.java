package org.lwj.MySpringBoot.login.dao;

import org.apache.ibatis.annotations.Mapper;
import org.lwj.MySpringBoot.login.entity.User;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface LoginDao {
	
	//获取数据库的用户名和密码，通过用户名获取
	User getSubject(String userName);
	
}
