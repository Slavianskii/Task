package com.taskmanager.tasks.models;


import java.io.Serializable;

import lombok.Builder;
import lombok.Data;


//Класс для формирования задач
@Data
@Builder
public class Task implements Serializable{
    
    private int id;
    private String title;
    private String description;
    private String priority;
    private String deadline;
    private String status;
    private String complete;
}

