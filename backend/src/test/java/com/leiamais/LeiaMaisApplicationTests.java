package com.leiamais;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.NONE,
		classes = LeiaMaisApplication.class
)
@ActiveProfiles("test")
class LeiaMaisApplicationTests {

	@Test
	void contextLoads() {
	}

}
