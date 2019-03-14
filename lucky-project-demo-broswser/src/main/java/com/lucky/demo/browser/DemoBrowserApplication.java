package com.lucky.demo.browser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.lucky"})
public class DemoBrowserApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoBrowserApplication.class,args);
    }
}
