package com.lucky.demo.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication(scanBasePackages = {"com.lucky"})
public class DemoAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoAppApplication.class,args);
    }
}
