package com.example.cityroutes;

import com.example.cityroutes.helper.Loader;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CityroutesApplicationTests {

	@Autowired
	Loader loader;

	@Test
	void contextLoads() {
	}
	@Test
	public void validateExisingRoute(){
		Assertions.assertThat(loader.checkRoute("Boston", "Albany")).isEqualTo("yes");
		Assertions.assertThat(loader.checkRoute("Trenton", "Albany")).isEqualTo("yes");
		Assertions.assertThat(loader.checkRoute("Philadelphia", "Boston")).isEqualTo("yes");


	}

	@Test
	public void validateNonExisingRoute(){
		Assertions.assertThat(loader.checkRoute("Boston", "Trenton")).isEqualTo("no");
		Assertions.assertThat(loader.checkRoute("Trenton", "Philadelphia")).isEqualTo("no");

	}


}
