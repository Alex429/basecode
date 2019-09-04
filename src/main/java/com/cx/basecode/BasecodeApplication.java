package com.cx.basecode;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cx.basecode.*.mapper")
public class BasecodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasecodeApplication.class, args);
	}

}
