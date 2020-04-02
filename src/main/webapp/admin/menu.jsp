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
    <title>Меню администратора</title>
    <style>
        <%@include file="../css/style.css"%>
    </style>
</head>
<body>
<jsp:include page="../admin_nav.jsp" />
<div class="row">
    <div class="col s12 m12">
        <div class="card-panel white">
    <p><a href="${path}/admin/polls/edit/">К редактору голосований</a></p>
    <p><a href="${path}/admin/list_users/">Список пользователей в системе</a></p>
    <p><a href="${path}/polls/result/">К результатам завершенных голосований</a></p>
    <p><a href="${path}/polls/open/">Список текущих голосований со статистикой</a></p>
        </div>
    </div>
</div>
<jsp:include page="../footer.jsp" />
</body>
</html>
