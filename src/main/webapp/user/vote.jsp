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
    <title>Проголосовать</title>
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

        <div class="row">
            <div class="col s12 m12">
                <div class="card">
                    <div class="card-content black-text">
                        <span class="card-title">Голосование "${question.poll.title}"</span>
                        <c:if test="${question == null}">
                            <p>В этом голосовании еще нет доступных вам вопросов, на которые вы еще не ответили.
                                Пожалуйста, вернитесь к списку доступных голосований <a href="${path}/user/open/">Список</a>  </p>
                        </c:if>

                        <c:if test="${question != null}">

                            <p>Вопрос ${question.question}</p>

                            <form action="" method="post">
                                <p>Выберете один из доступных вариантов:</p>
                                <div>

                                    <c:forEach var="variant" items="${question.variants}">
                                    <p>
                                        <input type="hidden" name="">
                                        <input type="radio" id="${variant.id}" style="opacity: 1.0; pointer-events: auto; position: relative; "
                                               name="variantId" value="${variant.id}">
                                        <label style="color: black; font-size: 1.4rem" for="${variant.id}">${variant.text}</label>
                                    </p>
                                    </c:forEach>
                                </div>
                                <div>
                                    <button type="submit">Проголосовать!</button>
                                </div>
                            </form>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    <jsp:include page="../footer.jsp" />
</body>
</html>