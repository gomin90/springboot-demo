package com.youwon.kafkademo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
// @EnableJpaRepositories(basePackages = "com.youwon.kafkademo.jpabiz.dao")
// @EntityScan(basePackages = "com.youwon.kafkademo.jpabiz.entity")
// @ComponentScan(basePackages = {"com.youwon.kafkademo.*"})
public class KafkademoApplication {
    public static void main(String[] args) {
        SpringApplication.run(KafkademoApplication.class, args);
    }
}
