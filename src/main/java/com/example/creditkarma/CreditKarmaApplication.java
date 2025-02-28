package com.example.creditkarma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.example.creditkarma.repository")  // ✅ Ensure repositories are detected
public class CreditKarmaApplication {
    public static void main(String[] args) {
        SpringApplication.run(CreditKarmaApplication.class, args);
    }
}
