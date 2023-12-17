package com.github.lltal.testlibbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.github.lltal.testlibbot.domain")
public class TestLibBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestLibBotApplication.class, args);
	}

}
