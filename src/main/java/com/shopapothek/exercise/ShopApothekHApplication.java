package com.shopapothek.exercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ShopApothekHApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopApothekHApplication.class, args);
    }

}
