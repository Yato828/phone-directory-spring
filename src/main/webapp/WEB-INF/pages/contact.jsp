<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Контакт</title>
</head>
<body>

<c:choose>
    <c:when test="${contact.id == null}">
        <h1>Новый контакт</h1>
    </c:when>
    <c:otherwise>
        <h1>Редактирование</h1>
    </c:otherwise>
</c:choose>

<c:choose>
    <c:when test="${contact.id == null}">
        <form action="/contacts/save" method="post">
    </c:when>
    <c:otherwise>
        <form action="/contacts/update" method="post">
            <input type="hidden" name="id" value="${contact.id}">
    </c:otherwise>
</c:choose>

    Имя: <input type="text" name="firstName" value="${contact.firstName}" required><br>
    Фамилия: <input type="text" name="lastName" value="${contact.lastName}" required><br>
    Отчество: <input type="text" name="middleName" value="${contact.middleName}"><br>
    Телефон: <input type="text" name="phone" value="${contact.phone}" required><br>
    Дата рождения: <input type="date" name="birthDate" value="${contact.birth}"><br>

    <button type="submit">Сохранить</button>
    <a href="/contacts/all">Отмена</a>
</form>
</body>
</html>