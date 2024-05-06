package com.taskmanager.tasks.Functions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.taskmanager.tasks.TasksApplication;
import com.taskmanager.tasks.WorkWithFile.LoadXmlDoc;
import com.taskmanager.tasks.WorkWithFile.SaveXmlDoc;
import com.taskmanager.tasks.models.Responce;
import com.taskmanager.tasks.models.Task;

public class UpdateTask {
    //Находим задачу по id и обновляем её поля
    public static Responce update(int id, Task task){
        Responce res = CheckTask.check(task);
        if (res.getFlag() == 1){
            return res;
        }
        Document doc = LoadXmlDoc.load(TasksApplication.filePath);
        Element root = doc.getDocumentElement();
        NodeList nodelist = root.getChildNodes();
        for (int i = 0; i < nodelist.getLength(); i++){
            Node node = nodelist.item(i);
            if(node.getNodeType() == Node.ELEMENT_NODE){
                Element elem = (Element) node;
                
                var elem_id = Integer.parseInt(elem.getElementsByTagName("id").item(0).getTextContent());
                if(elem_id == id){
                    elem.getElementsByTagName("title").item(0).setTextContent(task.getTitle());
                    elem.getElementsByTagName("description").item(0).setTextContent(task.getDescription());
                    elem.getElementsByTagName("priority").item(0).setTextContent(task.getPriority());
                    elem.getElementsByTagName("deadline").item(0).setTextContent(task.getDeadline());
                    elem.getElementsByTagName("status").item(0).setTextContent(task.getStatus());
                    if(task.getComplete() != null){
                        elem.appendChild(doc.createElement("complete"));
                        elem.getElementsByTagName("complete").item(0).
                        setTextContent(new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
                    }
                    SaveXmlDoc.save(doc);
                    return res;
                }
            }
        }
        res.setFlag(1);res.setStatus("ERROR");
        List<String> er = new ArrayList<String>();
        er.add("Id not found");
        res.setErrors(er);
        return res;
    }
}
