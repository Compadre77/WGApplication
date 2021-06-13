package com.webec.WGApplication;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WgApplicationTests {

	@BeforeAll
	public static void setupClas(){
		WebDriverManager.firefoxdriver().setup();
	}

	@Test
	void contextLoads() {
	}

}
