package org.lwj.MySpringBoot;



import java.util.Map;

import org.junit.jupiter.api.Test;
import org.lwj.MySpringBoot.login.entity.User;
import org.lwj.MySpringBoot.login.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MySpringBootApplicationTests {

	
	@Autowired
	RegisterService registerService;
	
	@Test
	void contextLoads(){
		
		User user = new User("lll","333333");
		Map<String, String> insertUser = registerService.register(user);
		System.out.println(insertUser.toString());
		
	}

}
