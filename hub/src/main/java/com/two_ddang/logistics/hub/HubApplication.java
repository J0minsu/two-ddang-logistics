package com.two_ddang.logistics.hub;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

//@OpenAPIDefinition(servers = {@Server(url = "/", description = "Default Server URL")})
@SpringBootApplication(
		scanBasePackages = {
				"com.two_ddang.logistics.hub",
				"com.two_ddang.logistics.core"
		}
)
@Slf4j
@EnableFeignClients
public class HubApplication {

	public static void main(String[] args) {
		SpringApplication.run(HubApplication.class, args);
	}

	@PostConstruct
	public void test() {
		log.info("hello world! hub module");
	}
}
