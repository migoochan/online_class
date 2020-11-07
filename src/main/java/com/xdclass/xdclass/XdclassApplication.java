package com.xdclass.xdclass;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.xdclass.xdclass.mapper")
@EnableTransactionManagement
public class XdclassApplication {

    public static void main(String[] args) {
        SpringApplication.run(XdclassApplication.class, args);
    }

}
