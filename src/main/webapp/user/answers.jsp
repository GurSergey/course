<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${pageContext.request.requestURL}</c:set>
<c:set var="uri" value="${pageContext.request.requestURI}" />
<c:set var="path" value="${fn:substring(url, 0, fn:length(url) -
 fn:length(uri))}${req.contextPath}" />


<html>

<head>
    <title>Список ответов голосования</title>
    <style>
        <%@include file="/css/style.css"%>
    </style>
</head>
<body>

<jsp:include page="../user_nav.jsp" />
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
<%--    <h1>Голосование - ${poll.title}</h1>--%>
<%--    <c:forEach var="question" items="${poll.questions}">--%>
<%--        <p>Вопрос ${question.question}</p>--%>
<%--        <p>Ваш ответ ${question.variants.get(0).text}</p>--%>
<%--        <br>--%>
<%--    </c:forEach>--%>
        <div class="row">
            <div class="col s12 m12">
                <div class="card">
                    <div class="card-content black-text">
                        <span class="card-title">Голосование - "${poll.title}"</span>
                            <ul class="collection">
                                <c:forEach var="question" items="${poll.questions}">
                                    <li class="collection-item avatar">

                                        <h6>Вопрос: ${question.question}</h6>
                                        <h6>Ваш ответ:</h6> <p> <br>
                                            ${question.variants.get(0).text}
                                        </p>
                                    </li>
                                </c:forEach>
                            </ul>
                    </div>
                    <div class="card-action">
                        <a href="${path}/user/polls/">Вернуться к списку моих голосований</a>
                    </div>
                </div>
            </div>
        </div>

<jsp:include page="../footer.jsp" />
</body>
</html>