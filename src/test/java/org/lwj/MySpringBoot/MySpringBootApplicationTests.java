package org.lwj.MySpringBoot;



import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MySpringBootApplicationTests {

	
	@Autowired()
	DataSource dataSource;
	@Test
	void contextLoads() {
		System.out.println(dataSource.getClass());
	}

}
