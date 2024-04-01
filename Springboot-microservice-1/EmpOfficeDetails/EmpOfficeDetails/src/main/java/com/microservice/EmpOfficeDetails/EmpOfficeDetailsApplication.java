package com.microservice.EmpOfficeDetails;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableFeignClients("com.microservice.EmpOfficeDetails.feignClients")
public class EmpOfficeDetailsApplication {

	@Value("${personaldetail.service.url}")
	private String personalDetailUrl;
	
	public static void main(String[] args) {
		SpringApplication.run(EmpOfficeDetailsApplication.class, args);
	}
	
	@Bean
	public WebClient webClient() {
		WebClient webClient = WebClient.builder()
				.baseUrl(personalDetailUrl).build();
		return webClient;
	}

}
