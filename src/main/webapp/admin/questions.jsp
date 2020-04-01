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
        <h1> Список вопросов в голосовании c идентификатором ${pollId}  </h1>
        <a href="${breadCrumbs}">Вернуться к списку голосований</a>
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
                    <th>Вопрос</th>
                    <th>Дата создания</th>
                </tr>
            </thead>
            <tbody>
                        <c:forEach var="question" items="${questions}">
                                 <tr>
                                    <td>${question.id}<input name="id" type="hidden" value="${question.id}" form="question_form_${question.id}">
                                        <input name="pollId" type="hidden" value="${pollId}" form="question_form_${question.id}"></td>
                                    <td><input name="title" type="text" value="${question.question}" form="question_form_${question.id}"></td>
                                    <td><input name="createDate" type="date" form="question_form_${question.id}"
                                               value="${question.createdDate}"></td>
                                    <td><input type="submit" name="update" value="Сохранить" form="question_form_${question.id}"></td>
                                    <td><input type="submit" name="delete" value="Удалить" form="question_form_${question.id}"></td>
                                    <td><a href="../../admin/variants/?questionId=${question.id}">
                                        <button>Перейти к редактированию вариантов</button></a></td>
                            </tr>
                        </c:forEach>
            </tbody>
        </table>
        </div>


        <div class="panel">
        <h3>Добавить новый вопрос</h3>

        <form action="" method="post" id="new_form"></form>

        <table>
            <thead>
            <tr>
                <th>Вопрос</th>
            </tr>
            </thead>
            <tr>
                <td>
                    <input name="title" type="text" value="" form="new_form">
<%--                    <input name="typeReq" type="hidden" value="save" form="new_form">--%>
                </td>
                <td><input type="submit" name="save" value="Сохранить" form="new_form"></td>
            </tr>
        </table>
        </div>
    </body>
</html>