<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Меню пользователя</title>
    <style>
        <%@include file="../css/style.css"%>
    </style>
</head>
<body>
<jsp:include page="../user_nav.jsp" />
<div class="row">
    <div class="col s12 m5">
        <div class="card-panel white">
            <p><a href="./open/">Перейти к списку открытых голосований</a></p>
            <p><a href="../polls/result/">К результатам завершенных голосований</a></p>
            <p><a href="./polls/">К списку голосований, в которях я участвовал</a></p>
            <p><a href="">К странице редактирования профиля</a></p>
        </div>
    </div>
</div>
<jsp:include page="../footer.jsp" />
<%--<div class="panel">--%>
<%--    <p><a href="./open/">Перейти к списку открытых голосований</a></p>--%>
<%--    <p><a href="../polls/result/">К результатам завершенных голосований</a></p>--%>
<%--    <p><a href="./polls/">К списку голосований, в которях я участвовал</a></p>--%>
<%--    <p><a href="">К странице редактирования профиля</a></p>--%>
<%--</div>--%>
</body>
</html>
