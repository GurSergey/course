<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Список моих голосований</title>
    <style>
        <%@include file="/css/style1.css"%>
    </style>
</head>
<body>
<jsp:include page="../user_nav.jsp" />
<%--<div class="panel">--%>

<%--    <table>--%>
<%--        <thead>--%>
<%--        <tr>--%>
<%--            <th>Название</th>--%>
<%--            <th>Дата начала голосования</th>--%>
<%--            <th>Дата завершения голосования </th>--%>
<%--            <th>Перейти к списку моих ответов</th>--%>
<%--        </tr>--%>
<%--        </thead>--%>
<%--        <tbody>--%>
<%--        <c:forEach var="poll" items="${polls}">--%>
<%--            <tr>--%>
<%--                <td>${poll.title}</td>--%>
<%--                <td>${poll.startDate}</td>--%>
<%--                <td>${poll.dateTo}</td>--%>
<%--                <td><a href="../answers?pollId=${poll.id}">Ответы</a></td>--%>
<%--            </tr>--%>
<%--        </c:forEach>--%>

<%--        </tbody>--%>
<%--    </table>--%>
<%--</div>--%>
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

<c:forEach var="poll" items="${polls}">
<div class="row">
    <div class="col s12 m5">
        <div class="card-panel white">

            <p>Название <br> ${poll.title}</p>
            <p>Дата начала голосования <br>${poll.startDate}</p>
            <p>Дата завершения <br> ${poll.dateTo}</p>
            <p><a href="../answers?pollId=${poll.id}">Перейти к мои ответам</a></p>
        </div>
    </div>
</div>
</c:forEach>

<jsp:include page="../footer.jsp" />
</body>
</html>
