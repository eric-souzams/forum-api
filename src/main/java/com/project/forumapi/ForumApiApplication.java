package com.project.forumapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ForumApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForumApiApplication.class, args);
    }

}
