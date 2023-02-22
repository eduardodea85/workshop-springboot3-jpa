package com.dea.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //Anotation para configurar esse projeto para que ele seja uma palicação do spring boot. O springboot, faz muito o uso de anotation para configurar o código. Faz um processamento na hora de compilar o código fonte.
public class CourseApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseApplication.class, args); //comando para rodar a aplicação do spring boot
	}

}
