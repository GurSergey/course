<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Редактирование профиля</title>
    <style>
        <%@include file="../css/style.css"%>
    </style>
</head>
<body>
<jsp:include page="../user_nav.jsp" />
<div class="panel">
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
            <div class="col s12 m6">
                <div class="card">
                    <div class="card-content black-text">
                        <span class="card-title">Вход в систему</span>
                        <form action="" method="post">
                            <p>Редактирование профиля</p>
                            <p>Имя: <input name="name" type="text" value="${voter.name}"></p>
                            <p>Пароль:<input name="password" type="password" value=""> </p>
                            <p>Повторите пароль:<input name="passwordRepeat" type="password" value=""></p>
                            <p>Телефон: <input name="phone" type="text" value="${voter.phone}"></p>
                            <p><input type="submit"></p>
                        </form>
                    </div>
                    <div class="card-action">
                        <p>Вернитесь в главное меню <a href="../user_auth">Вход</a></p>
                    </div>
                </div>
            </div>
        </div>

</div>
</body>
</html>
