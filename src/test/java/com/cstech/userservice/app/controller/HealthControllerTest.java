package com.cstech.userservice.app.controller;



import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.logging.Logger;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cstech.userservice.app.model.CustomResponseModel;

@SpringBootTest
public class HealthControllerTest {
	
	private static final Logger log = Logger.getLogger(HealthControllerTest.class.getName());
	
	@Autowired
	private HealthController healthController;
	
	@BeforeAll
	static void setup() {
	    log.info("@BeforeAll - executes once before all test methods in this class");
	}

	@BeforeEach
	void init() {
	    log.info("@BeforeEach - executes before each test method in this class");
	}	

	@DisplayName("Single test successful")
	@Test
	public void healthCheckTest() {
		CustomResponseModel<Boolean> res = healthController.healthCheck();
		assertThat(res.getSuccess()).isEqualTo(Boolean.TRUE);
	}	

	@Test
	@Disabled("Not implemented yet")
	void testShowSomething() {
	}	
	
	@AfterEach
	void tearDown() {
	    log.info("@AfterEach - executed after each test method.");
	}

	@AfterAll
	static void done() {
	    log.info("@AfterAll - executed after all test methods.");
	}	
	
	@Test
	void shouldThrowException() {
	    Throwable exception = assertThrows(UnsupportedOperationException.class, () -> {
	      throw new UnsupportedOperationException("Not supported");
	    });
	    assertEquals("Not supported", exception.getMessage());
	}

	@Test
	void assertThrowsException() {
	    String str = null;
	    assertThrows(IllegalArgumentException.class, () -> {
	      Integer.valueOf(str);
	    });
	}
	
}
