package com.example.demo;

import com.example.demo.model.Skill;
import com.example.demo.model.SkillArea;
import com.example.demo.service.dto.DtoMapping;
import com.example.demo.service.dto.SkillDTO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class DemoApplication {


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
