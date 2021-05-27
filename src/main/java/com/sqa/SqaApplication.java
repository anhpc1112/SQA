package com.sqa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.sqa.entity","com.sqa.repo"})
public class SqaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SqaApplication.class, args);
	}

}
