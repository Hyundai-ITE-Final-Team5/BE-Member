package com.mycompany.ite5bemember;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableCaching 
@EnableScheduling
@SpringBootApplication
public class Ite5BeMemberApplication {

	public static void main(String[] args) {
		SpringApplication.run(Ite5BeMemberApplication.class, args);
	}

}
