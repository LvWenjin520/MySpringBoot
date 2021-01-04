package org.lwj.MySpringBoot.login.dao;

import org.apache.ibatis.annotations.Mapper;
import org.lwj.MySpringBoot.login.entity.User;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface RegisterDao {
	
	//保存用户
	boolean insertUser(User user);
	
}
