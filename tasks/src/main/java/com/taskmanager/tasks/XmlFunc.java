package com.taskmanager.tasks;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.validator.GenericValidator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.taskmanager.tasks.models.Responce;
import com.taskmanager.tasks.models.Task;

//Класс содержит функции для работы с XML файлами

public class XmlFunc {
    private static String filePath = "tasks/src/main/resources/data.xml";
    
    //Парсим XML файл
    public static Document loadXmlDoc(String path){
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(path));
            
            return doc;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    //Формируем объект класса Task из значений элемента одного из узлов XML файла
    public static Task formTask(Element elem){
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
    //Забираем все задачи из файла
    public static List<Task> getAllTasks(){
        List<Task> retlst = new ArrayList<Task>();
        Document doc = loadXmlDoc(filePath);
        Element root = doc.getDocumentElement();
        NodeList nodelist = root.getChildNodes();
        for (int i = 0; i < nodelist.getLength(); i++){
            Node node = nodelist.item(i);
            if(node.getNodeType() == Node.ELEMENT_NODE){
                Element elem = (Element) node;
                retlst.add(XmlFunc.formTask(elem));
            }
        }
        return retlst;
    }
    //Находим задачу по id и обновляем её поля
    public static Responce updateTask(int id, Task task){
        Responce res = checkTask(task);
        if (res.getFlag() == 1){
            return res;
        }
        Document doc = loadXmlDoc(filePath);
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
                    saveXmlDoc(doc);
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
    //Сохраняем обновлённый XML документ
    private static void saveXmlDoc(Document doc){
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("tasks/src/main/resources/data.xml"));
            transformer.transform(source, result);
            
        } catch (TransformerConfigurationException | TransformerFactoryConfigurationError e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }


    //Формирует новый узел для вставки в документ
    private static Node formNewNode(Task task, Document doc){

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

    //Создаёт новую задачу, добавляет её в конец документа
    public static Responce CreateTaskFast(Task task){
        Responce res = checkTask(task);
        if(res.getFlag() == 1){
            return res;
        }
        Document doc = loadXmlDoc(filePath);
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
        root.appendChild(formNewNode(task, doc));
        saveXmlDoc(doc);
        return res;
    }

    //Удаляем задачу по id
    public static Responce deleteTask(int id){
        Document doc = loadXmlDoc(filePath);
        Element root = doc.getDocumentElement();
        NodeList nodelist = root.getChildNodes();
        for (int i = 0; i < nodelist.getLength(); i++){
            Node node = nodelist.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE){
                Element elem = (Element) node;
                
                var elem_id = Integer.parseInt(elem.getElementsByTagName("id").item(0).getTextContent());
                if (elem_id == id){
                    Task task = formTask(elem);
                    root.removeChild(node);
                    saveXmlDoc(doc);
                    return checkTask(task);
                }
            }
        }
        List<String> er = new ArrayList<String>();
        er.add("Id not found");
        Responce res = Responce.builder().flag(1).status("ERROR").errors(er).build();
        return res;
    }

    //Проверка задачи на соотвествоание требованиям
    public static Responce checkTask(Task task){
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
