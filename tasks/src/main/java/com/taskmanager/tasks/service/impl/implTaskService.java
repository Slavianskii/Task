package com.taskmanager.tasks.service.impl;


import java.util.List;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.taskmanager.tasks.XmlFunc;
import com.taskmanager.tasks.models.Responce;
import com.taskmanager.tasks.models.Task;
import com.taskmanager.tasks.service.TaskService;


//Реализация сервисного интерфейса.
@Service
public class implTaskService implements TaskService{

    @Override
    public List<Task> findAllTasks() {
        return XmlFunc.getAllTasks();
    }

    @Override
    public List<Task> findNewTask() {
        return XmlFunc.getAllTasks()
        .stream().filter(task -> "new".contains(task.getStatus())).collect(Collectors.toList());
    }

    @Override
    public List<Task> findDoneTask() {
        return XmlFunc.getAllTasks()
        .stream().filter(task -> "done".contains(task.getStatus())).collect(Collectors.toList());
    }

    @Override
    public List<Task> findInProgressTask() {
        return XmlFunc.getAllTasks()
        .stream().filter(task -> "in_progress".contains(task.getStatus())).collect(Collectors.toList());
    }

    @Override
    public Responce updateTaskbyId(int id, Task task) {
        return XmlFunc.updateTask(id, task);
    }

    @Override
    public Responce createNewTask(Task task) {
        return XmlFunc.CreateTaskFast(task);
    }

    @Override
    public Responce deleteTaskbyId(int id) {
        return XmlFunc.deleteTask(id);
    }
    
}