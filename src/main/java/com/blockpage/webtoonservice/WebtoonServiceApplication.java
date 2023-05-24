package com.blockpage.webtoonservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class WebtoonServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebtoonServiceApplication.class, args);
    }

}
