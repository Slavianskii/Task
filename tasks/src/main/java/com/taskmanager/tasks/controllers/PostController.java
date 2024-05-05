package com.taskmanager.tasks.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
public class PostController {
    private final TaskService service;

    @PostMapping("/update/{id}")
    public Responce updateTask(@PathVariable("id") int id, @RequestBody Task task){
        return service.updateTaskbyId(id, task);
    }
}
