# Task manager
Описание API:
* "api/getalltasks", "api/getnewtasks", "api/getprogrtasks", "api/getsdonetasks" - GET запросы, возвращающие XML файл, содержащий объекты класса Task, удовлетворяющих необходимому запросу.
* "api/update/{id}" - POST запрос, принимает XML файл и конвертирует его в объект класса Task, обновляет значения задачи по id.
* "api/newtask" - PUT запрос, принимает XML файл, преобразует его в объект класса Task, сохраняет полученный объект в XML файл.
* "api/delete/{id}" - DELETE запрос, удаляет задачу по заданному id.

Все данные задач хранятся в виде XML файла data.xml. 

Все запросы, кроме GET запросов, отсылают в ответ объект класса Responce в формате XML.

В ответе содержиться информация об операции: "OK", "ERROR", а также список допущенных ошибок.