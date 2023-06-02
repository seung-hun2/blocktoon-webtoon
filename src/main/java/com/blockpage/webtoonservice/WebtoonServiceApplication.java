package com.blockpage.webtoonservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableJpaAuditing
@EnableEurekaClient
@SpringBootApplication
@EnableScheduling
public class WebtoonServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebtoonServiceApplication.class, args);
    }

}
