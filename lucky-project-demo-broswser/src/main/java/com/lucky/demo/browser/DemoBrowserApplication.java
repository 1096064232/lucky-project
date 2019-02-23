package com.lucky.demo.browser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication(scanBasePackages = {"com.lucky"})
public class DemoBrowserApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoBrowserApplication.class,args);
    }
}
