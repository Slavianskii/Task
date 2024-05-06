package com.taskmanager.tasks.Functions;

import org.w3c.dom.Element;

import com.taskmanager.tasks.models.Task;

public class TaskForm {
    //Формируем объект класса Task из значений элемента одного из узлов XML файла
    public static Task form(Element elem){
        var id = Integer.parseInt(elem.getElementsByTagName("id").item(0).getTextContent());
        var title = elem.getElementsByTagName("title").item(0).getTextContent();
        var description = elem.getElementsByTagName("description").item(0).getTextContent();
        var priority = elem.getElementsByTagName("priority").item(0).getTextContent();
        var deadline = elem.getElementsByTagName("deadline").item(0).getTextContent();
        var status = elem.getElementsByTagName("status").item(0).getTextContent();
        try{
            var complete = elem.getElementsByTagName("complete").item(0).getTextContent();
            return Task.builder().id(id).title(title).description(description).priority(priority).deadline(deadline)
        .status(status).complete(complete).build();
        }
        catch(Exception e){
            return Task.builder().id(id).title(title).description(description).priority(priority).deadline(deadline)
        .status(status).build();
        }
    }
}
