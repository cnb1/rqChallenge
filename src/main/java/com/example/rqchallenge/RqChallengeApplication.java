package com.example.rqchallenge;

import com.example.rqchallenge.models.Employees;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RqChallengeApplication {
    public static void main(String[] args) {
        SpringApplication.run(RqChallengeApplication.class, args);
    }
}
