package com.webec.WGApplication;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WgApplicationTests {

	@BeforeAll
	public static void setupClas(){
		WebDriverManager.firefoxdriver().setup();
	}

	@Test
	void contextLoads() {

		// given
		ConverterController controller = new ConverterController();
		Model model = new ConcurrentModel();



	}

}
