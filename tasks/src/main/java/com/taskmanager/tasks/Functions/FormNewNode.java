package com.taskmanager.tasks.Functions;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.taskmanager.tasks.models.Task;

public class FormNewNode {
    //Формирует новый узел для вставки в документ
    public static Node form(Task task, Document doc){

        Element newNode = (Element)doc.createElement("Task");
                    
        newNode.appendChild(doc.createElement("id"));
        newNode.getElementsByTagName("id").item(0).setTextContent(Integer.toString(task.getId()));
                    
        newNode.appendChild(doc.createElement("title"));
        newNode.getElementsByTagName("title").item(0).setTextContent(task.getTitle());

        newNode.appendChild(doc.createElement("description"));
        newNode.getElementsByTagName("description").item(0).setTextContent(task.getDescription());

        newNode.appendChild(doc.createElement("priority"));
        newNode.getElementsByTagName("priority").item(0).setTextContent(task.getPriority());

        newNode.appendChild(doc.createElement("deadline"));
        newNode.getElementsByTagName("deadline").item(0).setTextContent(task.getDeadline());

        newNode.appendChild(doc.createElement("status"));
        newNode.getElementsByTagName("status").item(0).setTextContent(task.getStatus());
        return newNode;
    }
}
