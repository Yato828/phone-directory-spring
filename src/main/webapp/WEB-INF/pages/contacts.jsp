<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Телефонная книга</title>
</head>
<body>
<h1>Список контактов</h1>

<table border="1">
    <thead>
        <tr>
            <th>Имя</th><th>Фамилия</th><th>Отчество</th><th>Телефон</th><th>Дата рождения</th><th>Редактировать</th><th>Удалить</th>
        </tr>
    </thead>
    <tbody>
    <c:forEach items="${contacts}" var="c">
        <tr>
            <td>${c.firstName}</td>
            <td>${c.lastName}</td>
            <td>${c.middleName}</td>
            <td>${c.phone}</td>
            <td>${c.birth}</td>
            <td>
                <form action="/contacts/edit" method="get" style="display:inline;">
                    <input type="hidden" name="id" value="${c.id}">
                    <button type="submit">✏️</button>
                </form>
            </td>
            <td>
                <form action="/contacts/delete" method="post" style="display:inline;">
                    <input type="hidden" name="id" value="${c.id}">
                    <button type="submit" onclick="return confirm('Удалить?')">❌</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br/>
<a href="/contacts/add">➕ Добавить контакт</a>
</body>
</html>