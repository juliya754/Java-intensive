<%@ page import="model.TODO" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: ybessonova
  Date: 26.05.2019
  Time: 10:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ToDo</title>
</head>
<body>
<table>
    <caption>My ToDoList</caption>
    <tr>
        <th></th>
        <th>Id</th>
        <th>TODO</th>
        <th>IsDone?</th>
        <th></th>
    </tr>

<%
    List<TODO> todoList = (List<TODO>) request.getAttribute("todoList");

    for (TODO todoItem : todoList) {
%>
    <tr>
    <form action=${pageContext.request.contextPath}/user/todo method="post">
        <td><button type="submit" name="changeStatus" value=<%=todoItem.getId()%>>changeStatus</button></td>
    </form>


    <td><%=todoItem.getId()%></td>
    <td><%=todoItem.getComment()%></td>
    <td><%=todoItem.isDone()%></td>

<form action=${pageContext.request.contextPath}/user/todo method="post">
    <td><button type="submit" name="delete" value=<%=todoItem.getId()%>>delete</button></td>
</form>
</tr>
<%
    }
%>
    <form action = "${pageContext.request.contextPath}/user/todo" method = "post">
        <td></td>
        <td><input type = "text" name = "idtodo" size="3"></td>
        <td><input type = "text" name = "comment" size="30"></td>
        <td><input type = "text" name = "isdone" size="5"></td>
        <td><button type = "submit" name = "additem">add</button></td>
    </form>
</table>

</body>
</html>