<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Телефонная книга - Все контакты</title>

    <style>
        <%@ include file="style.css" %>
    </style>
</head>
<body>
<div class="container">
    <h1>📞 Список контактов</h1>

    <c:choose>
        <c:when test="${empty contacts}">
            <div class="empty-message">
                <p>📭 Нет сохраненных контактов</p>
                <a href="/contacts/add" class="add-link">➕ Добавить первый контакт</a>
            </div>
        </c:when>
        <c:otherwise>
            <table>
                <thead>
                    <tr>
                        <th>Имя</th>
                        <th>Фамилия</th>
                        <th>Отчество</th>
                        <th>Телефон</th>
                        <th>Дата рождения</th>
                        <th>Редактировать</th>
                        <th>Удалить</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${contacts}" var="contact">
                        <tr>
                            <td>${contact.firstName}</td>
                            <td>${contact.lastName}</td>
                            <td>${not empty contact.middleName ? contact.middleName : '-'}</td>
                            <td>${contact.phone}</td>
                            <td>${not empty contact.birth ? contact.birth : '-'}</td>
                            <td>
                                <form action="/contacts/edit" method="get" style="display:inline;">
                                    <input type="hidden" name="id" value="${contact.id}">
                                    <button type="submit" class="edit-btn">✏️ Редактировать</button>
                                </form>
                            </td>
                            <td>
                                <form action="/contacts/delete" method="post" style="display:inline;">
                                    <input type="hidden" name="id" value="${contact.id}">
                                    <button type="submit" class="delete-btn" onclick="return confirm('Удалить контакт ${contact.firstName} ${contact.lastName}?')">❌ Удалить</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <br/>
            <a href="/contacts/add" class="add-link">➕ Добавить новый контакт</a>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>