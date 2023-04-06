package com.nttdata.btc.operation.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class NttdataBtcOperationApplication {

	public static void main(String[] args) {
		SpringApplication.run(NttdataBtcOperationApplication.class, args);
	}

}
