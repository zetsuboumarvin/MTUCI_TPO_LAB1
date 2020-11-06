package com.mtuci.parking;

import com.mtuci.parking.controller.account.AccountController;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class ParkingApplicationTests {

	@Autowired
	private AccountController controller;

	@Test
	void contextLoads() {
		Assertions.assertNotNull(controller);
	}

}
