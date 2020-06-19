<%--
  Created by IntelliJ IDEA.
  User: siraj
  Date: 12.06.2020
  Time: 9:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">

    <title>Form</title>
</head>
<body>
<form action="/login" method="POST">
    <h1>Email:</h1> <input type="text" name="email" required><br>
    <h1>Пароль:</h1> <input type="text" name="password" required><br>
    <button><div>Отправить</div></button>
</form>
</body>
</html>
