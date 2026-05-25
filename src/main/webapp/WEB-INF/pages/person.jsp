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
                <c:when test="${empty person.id}">
                    <h1>➕ Новый контакт</h1>
                </c:when>
                <c:otherwise>
                    <h1>✏️ Редактирование контакта</h1>
                </c:otherwise>
            </c:choose>
            <form action="/persons/save" method="post">
                <c:if test="${not empty person.id}">
                    <input type="hidden" name="id" value="${person.id}">
                </c:if>

                <label>Имя:</label>
                <input type="text" name="firstName" value="${person.firstName}" required>

                <label>Фамилия:</label>
                <input type="text" name="lastName" value="${person.lastName}" required>

                <label>Отчество:</label>
                <input type="text" name="middleName" value="${person.middleName}">

                <c:if test="${empty person.id}">
                    <label>Основной номер телефона: </label>
                    <input type="text" name="phone" required>
                </c:if>

                <label>Дата рождения:</label>
                <input type="date" name="birthDate" value="${person.birth}">

                <div class="button-group">
                    <button type="submit" class="save-btn">💾 Сохранить</button>
                    <a href="/persons/all" class="cancel-link">❌ Отмена</a>
                </div>
            </form>
        </div>
    </body>
</html>