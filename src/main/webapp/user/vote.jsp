<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Проголосовать</title>
    <style>
        <%@include file="/css/style1.css"%>
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
    <c:if test="${question == null}">
        <p>В этом голосовании еще нет доступных вам вопросов, на которые вы еще не ответили.
            Пожалуйста, вернитесь к списку доступных голосований <a href=""> Список</a>  </p>
    </c:if>

    <c:if test="${question != null}">
        <h1>Заголовок голосования - ${question.poll.title}</h1>
        <h1>Вопрос ${question.question}</h1>

        <form action="" method="post">
            <p>Выберете один из доступных вариантов:</p>
            <div>
                <c:forEach var="variant" items="${question.variants}">
                    <input type="hidden" name="">
                <input type="radio" id="${variant.id}"
                       name="variantId" value="${variant.id}">
                <label for="${variant.id}">${variant.text}</label>
                </c:forEach>
            </div>
            <div>
                <button type="submit">Проголосовать!</button>
            </div>
        </form>
    </c:if>
</div>
</body>
</html>