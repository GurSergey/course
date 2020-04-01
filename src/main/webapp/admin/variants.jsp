<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Список голосований</title>
    <style>
        <%@include file="../css/style1.css"%>
    </style>
</head>
    <body>

    <div class="panel">
        <h1> Список вариантов в голосовании c идентификатором ${questionId}  </h1>
        <a href="${breadCrumbs}">Вернуться к списку вопросов</a>
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

        <c:forEach var="question" items="${questions}">
            <form action="" method="post" id="question_form_${question.id}"></form>
        </c:forEach>
        <table>
            <thead>
                <tr>
                    <th>id</th>
                    <th>Текст</th>
                    <th>Cохранить</th>
                    <th>Удалить</th>
                </tr>
            </thead>
            <tbody>
                        <c:forEach var="variant" items="${variants}">
                                 <tr>
                                    <td>${variant.id}<input name="id" type="hidden" value="${variant.id}" form="variant_form_${variant.id}">
                                        <input name="questionId" type="hidden" value="${questionId}" form="variant_form_${variant.id}"></td>
                                    <td><input name="title" type="text" value="${variant.text}" form="question_form_${variant.id}"></td>
                                    <td><input type="submit" name="update" value="Сохранить" form="question_form_${variant.id}"></td>
                                    <td><input type="submit" name="delete" value="Удалить" form="question_form_${variant.id}"></td>
                            </tr>
                        </c:forEach>
            </tbody>
        </table>
        </div>


        <div class="panel">
        <h3>Добавить новый вариант</h3>

        <form action="" method="post" id="new_form"></form>

        <table>
            <thead>
            <tr>
                <th>Текст</th>
                <th>Сохранить</th>
            </tr>
            </thead>
            <tr>
                <td>
                    <input name="text" type="text" value="" form="new_form">
                    <input name="questionId" type="hidden" value="${questionId}" form="new_form">
<%--                    <input name="typeReq" type="hidden" value="save" form="new_form">--%>
                </td>
                <td><input type="submit" name="save" value="Сохранить" form="new_form"></td>
            </tr>
        </table>
        </div>
    </body>
</html>