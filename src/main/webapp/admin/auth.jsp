<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Авторизация администратора</title>
    <style>
        <%@include file="../css/style1.css"%>
    </style>
</head>
<body>
<div class="panel">
    <c:if test="${!authPassed}">
        <form action="" method="post">
            <p>Вход в CMS OSES - Open Source Elections System</p>
            <p>Логин: <input name="login" type="text" value=""></p>
            <p>Пароль: <input name="password" type="password" value=""> </p>
        </form>
        <p> Секретный логин и пароль для входа в систему <span style="color: red"> admin 123 </span>
            Никому не рассказывайте о них </p>
    </c:if>
    <c:if test="${authPassed}">
        <p>Вы уже прошли авторизацию перейдите в главное меню </p>
    </c:if>

</div>
</body>
</html>
