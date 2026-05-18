<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Контакт</title>

    <style>
        <%@ include file="style.css" %>
    </style>
</head>
<body>
<div class="form-container">
    <c:choose>
        <c:when test="${empty contact.id}">
            <h1>➕ Новый контакт</h1>
        </c:when>
        <c:otherwise>
            <h1>✏️ Редактирование контакта</h1>
        </c:otherwise>
    </c:choose>

    <form action="${empty contact.id ? '/contacts/save' : '/contacts/update'}" method="post">
        <c:if test="${not empty contact.id}">
            <input type="hidden" name="id" value="${contact.id}">
        </c:if>

        <label>Имя *:</label>
        <input type="text" name="firstName" value="${contact.firstName}" required>

        <label>Фамилия *:</label>
        <input type="text" name="lastName" value="${contact.lastName}" required>

        <label>Отчество:</label>
        <input type="text" name="middleName" value="${contact.middleName}">

        <label>Телефон *:</label>
        <input type="text" name="phone" value="${contact.phone}" required>

        <label>Дата рождения:</label>
        <input type="date" name="birthDate" value="${contact.birth}">

        <div class="button-group">
            <button type="submit" class="save-btn">💾 Сохранить</button>
            <a href="/contacts/all" class="cancel-link">❌ Отмена</a>
        </div>
    </form>
</div>
</body>
</html>