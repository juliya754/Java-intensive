<%--
  Created by IntelliJ IDEA.
  User: ybessonova
  Date: 27.05.2019
  Time: 19:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action = "${pageContext.request.contextPath}/login" method = "post">
    <input type = "text" name = "login">
    <input type = "password" name = "password">
<input type = "submit" name = "login">
</form>
</body>
</html>
