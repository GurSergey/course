<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Список голосований</title>
    <style>
        <%@include file="./css/style.css"%>
    </style>
</head>
<body>
<div class="panel">
    <h1> Список голосований в системе </h1>
    <table>
        <thead>
            <tr>
                <th>id</th>
                <th>Название</th>
                <th>Видимость</th>
                <th>Дата создания</th>
                <th>Дата начала голосования</th>
                <th>Дата завершения голосования </th>
                <th>Cохранить изменения</th>
                <th>Удалить</th>
                <th>Редактировать вопросы</th>
            </tr>
        <thead>
        <c:forEach var="poll" items="${polls}">
            <tr>
                <td>${poll.id}</td>
                <td><input type="text" value="${poll.title}"></td>
                <td><input type="checkbox" value="${poll.visible}"></td>
                <td><input type="date"
                           value="${poll.createDate}"></td>
                <td><input type="date"
                           value="${poll.currentDate}"></td>
                <td><input type="date"
                       value="${poll.dateTo}"></td>
                <td><button>Сохранить</button></td>
                <td><button>Удалить</button></td>
                <td><a href=""><button>Перейти к редактированию вопросов</button></a></td>
            </tr>
        </c:forEach>
    </table>
    </div>


    <div class="panel">
    <h3>Добавить новое голосование</h3>
    <table>
        <thead>
        <tr>
            <th>Название</th>
            <th>Видимость</th>
            <th>Дата начала голосования</th>
            <th>Дата завершения голосования </th>
            <th>Cохранить изменения</th>
        </tr>
        </thead>
        <tr>
            <td><input type="text" value=""></td>
            <td><input type="checkbox" value=""></td>
                <td><input type="date"
                       value=""></td>
            <td><input type="date"
                       value=""></td>
            <td><button>Сохранить</button></td>
        </tr>
    </table>
    </div>
</body>
</html>