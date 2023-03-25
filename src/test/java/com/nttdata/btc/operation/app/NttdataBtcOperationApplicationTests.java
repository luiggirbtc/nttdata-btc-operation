package com.nttdata.btc.operation.app;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class NttdataBtcOperationApplicationTests {

	@Test
	void contextLoads() {
		String expected = "btc-operation";
		String actual = "btc-operation";

		assertEquals(expected, actual);
	}
}