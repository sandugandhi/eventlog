package com.company.eventlog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(args={"./logs/log.txt"})
@TestPropertySource(locations="classpath:application-integrationtest.properties")
class EventLogApplicationTests {

	@Test
	void contextLoads() {
	}

}
