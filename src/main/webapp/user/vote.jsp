<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Проголосовать</title>
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
    <h1>Заголовок голосования - ${question.poll.title}</h1>
    <h1>Вопрос ${question.question}</h1>

    <form>
        <p>Please select your preferred contact method:</p>
        <div>
            <c:forEach var="variant" items="${question.variants}">
            <input type="radio" id="${variant.id}"
                   name="variant" value="${variant.id}" checked>
            <label for="${variant.id}">${variant.text}</label>

            </c:forEach>
        </div>
        <div>
            <button type="submit">Submit</button>
        </div>
    </form>

</div>
</body>
</html>