package com.coding.oj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.coding.oj.mapper")
@SpringBootApplication
public class OjJudgeServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OjJudgeServerApplication.class, args);
    }

}
