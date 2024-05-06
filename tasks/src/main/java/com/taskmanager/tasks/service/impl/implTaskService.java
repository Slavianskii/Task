package com.taskmanager.tasks.service.impl;


import java.util.List;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.taskmanager.tasks.Functions.CreateNewTask;
import com.taskmanager.tasks.Functions.DeleteTask;
import com.taskmanager.tasks.Functions.GetAllTasks;
import com.taskmanager.tasks.Functions.UpdateTask;
import com.taskmanager.tasks.models.Responce;
import com.taskmanager.tasks.models.Task;
import com.taskmanager.tasks.service.TaskService;


//Реализация сервисного интерфейса.
@Service
public class implTaskService implements TaskService{

    @Override
    public List<Task> findAllTasks() {
        return GetAllTasks.get();
    }

    @Override
    public List<Task> findNewTask() {
        return GetAllTasks.get()
        .stream().filter(task -> "new".contains(task.getStatus())).collect(Collectors.toList());
    }

    @Override
    public List<Task> findDoneTask() {
        return GetAllTasks.get()
        .stream().filter(task -> "done".contains(task.getStatus())).collect(Collectors.toList());
    }

    @Override
    public List<Task> findInProgressTask() {
        return GetAllTasks.get()
        .stream().filter(task -> "in_progress".contains(task.getStatus())).collect(Collectors.toList());
    }

    @Override
    public Responce updateTaskbyId(int id, Task task) {
        return UpdateTask.update(id, task);
    }

    @Override
    public Responce createNewTask(Task task) {
        return CreateNewTask.create(task);
    }

    @Override
    public Responce deleteTaskbyId(int id) {
        return DeleteTask.delete(id);
    }
    
}