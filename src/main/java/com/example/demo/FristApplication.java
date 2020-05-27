package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@ComponentScan(basePackages= "com.example")//添加扫包@ComponentScan(basePackages= "")
@EnableAutoConfiguration
public class FristApplication {
	public static void main(String[] args) {
		SpringApplication.run(FristApplication.class, args);
	}

}
