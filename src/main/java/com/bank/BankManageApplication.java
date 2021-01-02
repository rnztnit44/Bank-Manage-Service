package com.bank;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.bank")
@EnableJpaRepositories
@EnableAutoConfiguration
public class BankManageApplication  {
    public static void main(String[] args) {
        SpringApplication.run(BankManageApplication.class, args);
    }

}