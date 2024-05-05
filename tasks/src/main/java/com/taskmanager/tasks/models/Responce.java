package com.taskmanager.tasks.models;

import java.io.Serializable;
import java.util.List;

import lombok.Builder;
import lombok.Data;

//Класс для формирования ответов и отправки ошибок
@Data
@Builder
public class Responce implements Serializable{
    private int flag;
    private String status;
    private List<String> errors;
}
