package com.yjh.whoplayer;

import jakarta.annotation.sql.DataSourceDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class WhoPlayErApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhoPlayErApplication.class, args);
    }

}
