package com.taskmanager.tasks.service;

import java.util.List;

import com.taskmanager.tasks.models.Responce;
import com.taskmanager.tasks.models.Task;


//Сервирсный интерфейс, описывающий функции
public interface TaskService {

    List<Task> findAllTasks();
    List<Task> findNewTask();
    List<Task> findInProgressTask();
    List<Task> findDoneTask();
    Responce updateTaskbyId(int id, Task task);
    Responce createNewTask(Task task);
    Responce deleteTaskbyId(int id);

}
