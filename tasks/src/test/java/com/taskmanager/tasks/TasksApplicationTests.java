package com.taskmanager.tasks;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.taskmanager.tasks.Functions.CheckTask;
import com.taskmanager.tasks.WorkWithFile.LoadXmlDoc;
import com.taskmanager.tasks.models.Task;

@SpringBootTest
class TasksApplicationTests {
	private String filePath = "src/main/resources/data.xml";
	@Test
	void loadXml_shouldLoadNotNull() {
		var doc = LoadXmlDoc.load(filePath);

		assertTrue(doc != null);
	}

	@Test
	void checkTask_shouldReturnError(){
		Task task = Task.builder().id(1).title("qwertyui").description("someDescr").priority("3")
		.deadline("2012-23-23").status("new").build();
		var res = CheckTask.check(task);
		assertTrue(res.getFlag() == 1);
	};



}
