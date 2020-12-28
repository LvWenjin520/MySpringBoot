package org.lwj.MySpringBoot.mcstudy.dao;

import org.apache.ibatis.annotations.Mapper;
import org.lwj.MySpringBoot.mcstudy.entity.User;
import org.springframework.stereotype.Repository;
@Mapper
@Repository
public interface McStudyMapper {
	
	//查询方法
	public User query(int id);
	
}
