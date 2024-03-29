package com.mari.engine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class WebQuizEngine {

    public static void main(String[] args) {
        SpringApplication.run(WebQuizEngine.class, args);
    }

}
