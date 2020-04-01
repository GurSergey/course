<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Авторизация пользователя</title>
    <style>
        <%@include file="../css/style.css"%>
    </style>
</head>
<body>
<%--<div class="panel">--%>
<%--    <c:if test="${!authPassed}">--%>
<%--        <form action="" method="post">--%>
<%--            <p>Для того чтобы продолжить пользоваться вохможностями OSES авторизируйтесь пожалуйста</p>--%>
<%--            <p>Вход для пользователей OSES</p>--%>
<%--            <p>Логин: <input name="login" type="text" value=""></p>--%>
<%--            <p>Пароль: <input name="password" type="password" value=""> </p>--%>
<%--            <p><input type="submit" ></p>--%>
<%--        </form>--%>
<%--        <p>Либо пройдите процедуру регистрации <a href="../user_registration">Регистрация</a></p>--%>

<%--    </c:if>--%>
<%--    <c:if test="${authPassed}">--%>
<%--        <p>Вы уже прошли авторизацию перейдите в главное меню <a href="../user/menu"> Ссылка на меню </a></p>--%>
<%--    </c:if>--%>

<%--</div>--%>
<jsp:include page="../nav.jsp" />
<div class="row">
    <div class="col s12 m6">
        <div class="card">
            <div class="card-content black-text">
                <span class="card-title">Вход в систему</span>
                <c:if test="${!authPassed}">
                    <form action="" method="post">
                        <p>Для того чтобы продолжить пользоваться вохможностями OSES авторизируйтесь пожалуйста</p>
                        <p>Вход для пользователей OSES</p>
                        <p>Логин: <input name="login" type="text" value=""></p>
                        <p>Пароль: <input name="password" type="password" value=""> </p>
                        <p><input type="submit" ></p>
                    </form>
                </c:if>
                <c:if test="${authPassed}">
                    <p>Вы уже прошли авторизацию перейдите в главное меню <a href="../user/menu"> Ссылка на меню </a></p>
                </c:if>
            </div>
            <div class="card-action">
                Либо пройдите процедуру регистрации <a href="../user_registration">Регистрация</a>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../footer.jsp" />
</body>
</html>
