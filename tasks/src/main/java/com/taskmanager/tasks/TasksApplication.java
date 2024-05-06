package com.taskmanager.tasks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TasksApplication {
	public static String filePath = "tasks/src/main/resources/data.xml";
	public static void main(String[] args) {
		SpringApplication.run(TasksApplication.class, args);
	}

}
