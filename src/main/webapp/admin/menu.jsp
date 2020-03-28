<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Меню администратора</title>
    <style>
        <%@include file="../css/style.css"%>
    </style>
</head>
<body>
<div class="panel">
    <p><a href="./polls/">К редактору голосований</a></p>
    <p><a href="./list_users/">Список пользователей в системе</a></p>
    <p><a href="../../polls/result/">К результатам завершенных голосований</a></p>
    <p><a href="../../polls/open/">Список текущих голосований со статистикой</a></p>
</div>
</body>
</html>
