package com.taskmanager.tasks.Functions;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.validator.GenericValidator;

import com.taskmanager.tasks.models.Responce;
import com.taskmanager.tasks.models.Task;

public class CheckTask {
    //Проверка задачи на соотвествоание требованиям
    public static Responce check(Task task){
        int flag = 0;
        String status = "OK";
        List<String> errors = new ArrayList<String>();
        if (task.getTitle().length() > 50){
            flag = 1;
            status = "ERROR";
            errors.add("Title longer than 50");
        }
        if(!GenericValidator.isDate(task.getDeadline(), "yyyy-MM-dd", true)){
            flag = 1;
            status = "ERROR";
            errors.add("Deadline is not a date");
        }
        boolean isInteger = task.getPriority().matches("-?\\d+");
        if (isInteger){
            int pr = Integer.parseInt(task.getPriority());
            if( pr < 0 || pr > 10){
                flag = 1;
                status = "ERROR";
                errors.add("Priority out of range");
            }
        }
        else{
            flag = 1;
            status = "ERROR";
            errors.add("Priority is not a digit");
        }

        return Responce.builder().flag(flag).status(status).errors(errors).build();
    }
}
