package com.taskmanager.tasks.Functions;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.taskmanager.tasks.TasksApplication;
import com.taskmanager.tasks.WorkWithFile.LoadXmlDoc;
import com.taskmanager.tasks.WorkWithFile.SaveXmlDoc;
import com.taskmanager.tasks.models.Responce;
import com.taskmanager.tasks.models.Task;

public class CreateNewTask {
    //Создаёт новую задачу, добавляет её в конец документа
    public static Responce create(Task task){
        Responce res = CheckTask.check(task);
        if(res.getFlag() == 1){
            return res;
        }
        Document doc = LoadXmlDoc.load(TasksApplication.filePath);
        Element root = doc.getDocumentElement();
        Node node = root.getLastChild();
        int newId;
        while(node != null){
            if(node.getNodeType() == Node.ELEMENT_NODE) break;
            node = node.getPreviousSibling(); 
        } 
        
        if (node == null){
            task.setId(1);
        }else{
            Element last_node = (Element) node;
            newId = Integer.parseInt(last_node.getElementsByTagName("id").item(0).getTextContent()) + 1;
            task.setId(newId);
        }
        
        task.setStatus("new");
        root.appendChild(FormNewNode.form(task, doc));
        SaveXmlDoc.save(doc);
        return res;
    }
}
