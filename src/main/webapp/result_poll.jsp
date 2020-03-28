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
    <h1>Заголовок голосования - ${poll.title}</h1>
    <jsp:useBean id="polls" scope="request" type="java.util.List"/>
    <c:forEach var="question" items="${poll.questions}">
        <p>${question.title}</p>
        <p>Всего высказалось по этому вопросу - ${question.questionsStatistics.countAnswers} человек</p>
        <c:forEach var="variant" items="${question.variants}">
            <p>${variant.text}. количество проголосовавших ${variant.variantStatistics.countAnswers} Процент
                    ${variant.variantStatistics.countAnswers/question.questionsStatistics.countAnswers}</p>
        </c:forEach>
        <br>
    </c:forEach>


</div>
</body>
</html>
