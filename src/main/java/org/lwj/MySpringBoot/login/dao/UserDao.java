package org.lwj.MySpringBoot.login.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserDao {
	
	//查询用户数量
	int queryUserNumByUserName(String userName);
}
