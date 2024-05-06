package com.taskmanager.tasks.Functions;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.taskmanager.tasks.TasksApplication;
import com.taskmanager.tasks.WorkWithFile.LoadXmlDoc;
import com.taskmanager.tasks.WorkWithFile.SaveXmlDoc;
import com.taskmanager.tasks.models.Responce;

public class DeleteTask {
    //Удаляем задачу по id
    public static Responce delete(int id){
        Document doc = LoadXmlDoc.load(TasksApplication.filePath);
        Element root = doc.getDocumentElement();
        NodeList nodelist = root.getChildNodes();
        for (int i = 0; i < nodelist.getLength(); i++){
            Node node = nodelist.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE){
                Element elem = (Element) node;
                
                var elem_id = Integer.parseInt(elem.getElementsByTagName("id").item(0).getTextContent());
                if (elem_id == id){
                    root.removeChild(node);
                    SaveXmlDoc.save(doc);
                    return CheckTask.check(TaskForm.form(elem));
                }
            }
        }
        List<String> er = new ArrayList<String>();
        er.add("Id not found");
        Responce res = Responce.builder().flag(1).status("ERROR").errors(er).build();
        return res;
    }
}
