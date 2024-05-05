package com.taskmanager.tasks.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.taskmanager.tasks.models.Task;
import com.taskmanager.tasks.service.TaskService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/api", produces = "application/xml")
@AllArgsConstructor
public class GetController {

    private final TaskService service;
    @GetMapping("/alltasks")
    public List<Task> GetAllTasks(){
        return service.findAllTasks();
    }
    @GetMapping("/newtasks")
    public List<Task> GetNewTasks(){
        return service.findNewTask();
    }
    @GetMapping("/progrtasks")
    public List<Task> GetInProgressTasks(){
        return service.findInProgressTask();
    }
    @GetMapping("/donetasks")
    public List<Task> GetDoneTasks(){
        return service.findDoneTask();
    }
}
