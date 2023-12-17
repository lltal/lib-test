package com.github.lltal.testlibbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class TestLibBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestLibBotApplication.class, args);
	}

}
