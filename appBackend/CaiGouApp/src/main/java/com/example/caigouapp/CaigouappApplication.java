package com.example.caigouapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CaigouappApplication {

    public static void main(String[] args) {
        SpringApplication.run(CaigouappApplication.class, args);
    }

}
