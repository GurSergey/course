<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Список голосований</title>
    <style>
        <%@include file="../css/style.css"%>
    </style>
</head>
    <body>

    <div class="panel">
        <h1> Список голосований в системе </h1>
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
<%--        <jsp:useBean id="polls" scope="request" type="java.util.List"/>--%>
        <c:forEach var="poll" items="${polls}">
            <form action="" method="post" id="poll_form_${poll.id}"></form>
        </c:forEach>
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
            </thead>
            <tbody>
                        <c:forEach var="poll" items="${polls}">
                                 <tr>
                                    <td>${poll.id}<input name="id" type="hidden" value="${poll.id}" form="poll_form_${poll.id}">
                                        <input name="typeReq" type="hidden" value="update" form="poll_form_${poll.id}"></td>
                                    <td><input name="title" type="text" value="${poll.title}" form="poll_form_${poll.id}"></td>
                                    <td><input name="visible" type="checkbox" value="${poll.visible}" form="poll_form_${poll.id}"></td>
                                    <td><input name="createDate" type="date" form="poll_form_${poll.id}"
                                               value="${poll.createDate}"></td>
                                    <td><input name="startDate" type="date" form="poll_form_${poll.id}"
                                               value="${poll.startDate}"></td>
                                    <td><input name="dateTo" type="date" form="poll_form_${poll.id}"
                                           value="${poll.dateTo}"></td>
                                    <td><input type="submit" name="update" value="Сохранить" form="poll_form_${poll.id}"></td>
                                    <td><input type="submit" name="delete" value="Удалить" form="poll_form_${poll.id}"></td>
                                    <td><a href="admin/questions/?poll_id=${poll.id}">
                                        <button>Перейти к редактированию вопросов</button></a></td>
                            </tr>
                        </c:forEach>
            </tbody>
        </table>
        </div>


        <div class="panel">
        <h3>Добавить новое голосование</h3>

        <form action="" method="post" id="new_form"></form>

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
                <td><input name="title" type="text" value="" form="new_form">
                    <input name="typeReq" type="hidden" value="save" form="new_form">
                </td>
                <td><input name="visible" type="checkbox" value="" form="new_form"></td>
                <td><input name="startDate" type="date" value="" form="new_form"></td>
                <td><input name="dateTo" type="date" value="" form="new_form"></td>
                <td><input type="submit" name="save" value="Сохранить" form="new_form"></td>
            </tr>
        </table>
        </div>
    </body>
</html>