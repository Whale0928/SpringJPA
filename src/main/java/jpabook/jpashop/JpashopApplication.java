package jpabook.jpashop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpashopApplication {

	public static void main(String[] args) {

		//시작시 h2 DB 시작 필요
		SpringApplication.run(JpashopApplication.class, args);
	}

}