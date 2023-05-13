package com.codea;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootTest
@EnableJpaAuditing
class WebApplicationTests {
    public static void main(String[] args) {
        SpringApplication.run(WebApplicationTests.class, args);
    }


}
