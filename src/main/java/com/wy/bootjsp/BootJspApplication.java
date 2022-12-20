package com.wy.bootjsp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wy.bootjsp.mapper")
public class BootJspApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootJspApplication.class, args);
	}

}
