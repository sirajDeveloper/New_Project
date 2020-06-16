<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
    <title>Java Mentor Users App</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: orangered">
        <div>
            <a href="https://javalearn.online/" class="navbar-brand"> Java Mentor Users App </a>
        </div>

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/admin" class="nav-link">Пользователи</a></li>
        </ul>
    </nav>
</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${user != null}">
            <form action="/admin/update" method="post">
                </c:if>
                <c:if test="${user == null}">
                <form action="/admin/insert" method="post">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${user != null}">
                                Редактировать пользователя
                            </c:if>
                            <c:if test="${user == null}">
                                Добавить нового пользователя
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${user != null}">
                        <input type="hidden" name="id" value="<c:out value='${user.id}' />" />
                    </c:if>

                    <fieldset class="form-group">
                        <label>Имя</label> <input type="text" value="<c:out value='${user.name}' />" class="form-control" name="name" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Email</label> <input type="text" value="<c:out value='${user.email}' />" class="form-control" name="email" required>
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Пароль</label> <input type="text" value="<c:out value='${user.password}' />" class="form-control" name="password" required>
                    </fieldset>

                        <fieldset class="form-group">
                            <label>Доступ</label>
                            <p>user <input type="radio" value="<c:out value="user" />" class="form-control" name="role">
                            <p>admin <input type="radio" value="<c:out value="admin" />" class="form-control" name="role">
                        </fieldset>

                    <button type="submit" class="btn btn-success">Сохранить</button>
                </form>
        </div>
    </div>
</div>
</body>

</html>
