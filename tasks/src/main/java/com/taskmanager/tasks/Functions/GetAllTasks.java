package com.taskmanager.tasks.Functions;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.taskmanager.tasks.TasksApplication;
import com.taskmanager.tasks.WorkWithFile.LoadXmlDoc;
import com.taskmanager.tasks.models.Task;

public class GetAllTasks {
    //Забираем все задачи из файла
    public static List<Task> get(){
        List<Task> retlst = new ArrayList<Task>();
        Document doc = LoadXmlDoc.load(TasksApplication.filePath);
        Element root = doc.getDocumentElement();
        NodeList nodelist = root.getChildNodes();
        for (int i = 0; i < nodelist.getLength(); i++){
            Node node = nodelist.item(i);
            if(node.getNodeType() == Node.ELEMENT_NODE){
                Element elem = (Element) node;
                retlst.add(TaskForm.form(elem));
            }
        }
        return retlst;
    }
}
