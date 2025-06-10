package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.Collections;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(DemoApplication.class);
        
        // Imposta il profilo di default se non specificato
        app.setDefaultProperties(Collections.singletonMap("spring.profiles.default", "dev"));
        
        app.run(args);
    }
}