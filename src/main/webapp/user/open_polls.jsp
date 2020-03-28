<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Список открытых голосований</title>
    <style>
        <%@include file="/css/style.css"%>
    </style>
</head>
<body>
<div class="panel">
    <%@ page import="com.company.enums.EntityError" %>
    <c:if test="${error==EntityError.NO_ERROR_UPDATE}">
        <p style="color: green;">Запись успешно обновлена</p>
    </c:if>
    <c:if test="${error==EntityError.NO_ERROR_DELETE}">
        <p style="color: green;">Запись успешно удалена</p>
    </c:if>
    <c:if test="${error==EntityError.NO_ERROR_INSERT}">
        <p style="color: green;">Запись успешно добавлена</p>
    </c:if>
    <c:if test="${error==EntityError.INSERT}">
        <p style="color: red;">Возникла ошибка вставки новых записей. Повторите попытку позже</p>
    </c:if>
    <c:if test="${error==EntityError.SELECT}">
        <p style="color: red;">Возникла ошибка получение записей из БД. Повторите попытку позже</p>
    </c:if>
    <c:if test="${error==EntityError.UPDATE}">
        <p style="color: red;">Возникла ошибка обновления записей из БД. Повторите попытку позже</p>
    </c:if>
    <table>
        <thead>
        <tr>
            <th>Название</th>
            <th>Дата начала голосования</th>
            <th>Дата завершения голосования </th>
            <th>Список проголосовавших</th>
            <th>Принято мнений в рамках голосования</th>
            <th>Перейти к процессу голосования</th>
            <th>Перейти к подробной информации о голосовании</th>
        </tr>
        </thead>
        <tbody>
        <jsp:useBean id="polls" scope="request" type="java.util.List"/>
        <c:forEach var="poll" items="${polls}">
            <tr>
                <td>${poll.title}></td>
                <td>${poll.startDate}</td>
                <td>${poll.dateTo}</td>
                <td>${poll.statistics.getCountVoter}</td>
                <td>${poll.statistics.getCountOpinions}</td>
                <td><a href="">Голосовать!</a></td>
                <td><a href="">Подробно</a></td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
</div>
</body>
</html>
