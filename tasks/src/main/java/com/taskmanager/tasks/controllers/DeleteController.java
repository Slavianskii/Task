package com.taskmanager.tasks.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskmanager.tasks.models.Responce;
import com.taskmanager.tasks.service.TaskService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/api", produces = "application/xml")
@AllArgsConstructor
public class DeleteController {
    private final TaskService service;

    @DeleteMapping("/delete/{id}")
    public Responce deleteTask(@PathVariable("id") int id){
        return service.deleteTaskbyId(id);
    }
}
