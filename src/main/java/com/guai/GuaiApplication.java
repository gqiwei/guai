package com.guai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.guai.*.dao")
public class GuaiApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuaiApplication.class, args);
    }

}
