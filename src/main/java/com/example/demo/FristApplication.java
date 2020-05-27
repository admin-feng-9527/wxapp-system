package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;



@ComponentScan(basePackages= "com.example")//添加扫包@ComponentScan(basePackages= "")
@EnableAutoConfiguration
public class FristApplication {
	public static void main(String[] args) {
		SpringApplication.run(FristApplication.class, args);
	}

}
