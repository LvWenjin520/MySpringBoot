package org.lwj.MySpringBoot;

import org.junit.jupiter.api.Test;
import org.lwj.MySpringBoot.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MySpringBootApplicationTests {

	
	@Autowired()
	//@Qualifier("my")
	private Person person;
	
	@Test
	void contextLoads() {
		System.out.println(person);
	}

}
