<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Список голосований</title>
</head>
<body>
<table>
    <tr>
        <th>id</th>
        <th>Название</th>
        <th>Видимость</th>
        <th>Дата создания</th>
        <th>Дата начала голосования</th>
        <th>Дата завершения голосования </th>
        <th>Кнопка сохранить</th>
    </tr>
    <c:forEach var="poll" items="${polls}">
        <tr>
            <td>${poll.id}</td>
            <td>${poll.title}</td>
            <td>${poll.visible}</td>
            <td><input type="date" id="start" name="trip-start"
                       value="${poll.createDate}"></td>
            <td><input type="date" id="start123" name="trip-start"
                       value="${poll.currentDate}"></td>
            <input type="date" id="start1" name="trip-start"
                   value="${poll.dateTo}"></td>
            <button>Отправить</button>
        </tr>
    </c:forEach>
</table>
</body>
</html>