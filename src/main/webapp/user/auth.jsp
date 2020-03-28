<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Авторизация администратора</title>
    <style>
        <%@include file="../css/style.css"%>
    </style>
</head>
<body>
<div class="panel">
    <c:if test="${!authPassed}">
        <form action="" method="post">
            <p>Вход для пользователей OSES</p>
            <p>Логин: <input name="login" type="text" value=""></p>
            <p>Пароль: <input name="password" type="password" value=""> </p>
        </form>

    </c:if>
    <c:if test="${authPassed}">
        <p>Вы уже прошли авторизацию перейдите в главное меню <a> Ссылка на меню </a></p>
    </c:if>

</div>
</body>
</html>
