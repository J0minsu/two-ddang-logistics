package com.two_ddang.logistics.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication(
		scanBasePackages = {
				"com.two_ddang.logistics.order",
				"com.two_ddang.logistics.core"
		}
)
@EnableFeignClients
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class OrderApplication{

	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}

}
