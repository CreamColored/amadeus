package com.amadeus.test.fastdfs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class TestFastDFSApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestFastDFSApplication.class, args);
    }
}
