package com.imie.money;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@ComponentScan("com.imie.money")
public class MoneyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoneyApplication.class, args);
    }
}

