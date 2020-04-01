<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Выход пользователя</title>
    <style>
        <%@include file="../css/style.css"%>
    </style>
</head>
<body>
<jsp:include page="../nav.jsp" />
<div class="panel">
    Вы вышли из системы, можете перейти на главную страницу <a href="../index.html">Главная страница</a>
</div>
<jsp:include page="../footer.jsp" />
</body>
</html>
