package com.hanxi.spzx.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//Knife4j不在同一个模块，需要扫包
//@ComponentScan(basePackages = {"org"})
//@MapperScan(basePackages = ("com.hanxi.spzx.manager"))
//@ComponentScan(basePackages = {"com.hanxi.spzx"})
public class ManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class,args);
    }
}
