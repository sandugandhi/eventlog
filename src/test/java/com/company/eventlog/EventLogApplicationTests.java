package com.company.eventlog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(args={"./logs/log.txt"})
class EventLogApplicationTests {

	@Test
	void contextLoads() {
	}

}
