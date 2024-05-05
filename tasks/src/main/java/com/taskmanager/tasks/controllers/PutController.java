package com.taskmanager.tasks.controllers;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskmanager.tasks.models.Responce;
import com.taskmanager.tasks.models.Task;
import com.taskmanager.tasks.service.TaskService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/api", consumes = "application/xml", produces = "application/xml")
@AllArgsConstructor
public class PutController {
    private final TaskService service;

    @PutMapping("/newtask")
    public Responce addNewTaskFast(@RequestBody Task task){
        return service.createNewTask(task);
    }
}
