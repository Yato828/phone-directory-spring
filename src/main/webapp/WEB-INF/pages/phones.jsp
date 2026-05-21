<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Телефоны контакта</title>
    <style>
            <%@ include file="style.css" %>
        </style>
</head>
<body>
<div class="container">
    <h1>📞 Телефоны: ${contact.firstName} ${contact.lastName}</h1>

    <div style="margin-bottom: 20px;">
        <h3>📱 Основной номер: ${contact.phone}</h3>
    </div>

    <h3>➕ Дополнительные номера:</h3>

    <c:if test="${empty phones}">
        <p>❌ Нет дополнительных номеров</p>
    </c:if>

    <table>
        <thead>
            <tr>
                <th>Номер телефона</th>
                <th>Тип</th>
                <th>Действия</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${phones}" var="phone">
                <tr>
                    <td>${phone.number}</td>
                    <td>${phone.type != null ? phone.type : 'не указан'}</td>
                    <td>
                        <a href="/contacts/phones/delete/${phone.id}"
                           onclick="return confirm('Удалить номер ${phone.number}?')"
                           class="delete-btn">❌ Удалить</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <br/>
    <a href="/contacts/phones/add/${contact.id}" class="add-link">➕ Добавить номер</a>
    <br/><br/>
    <a href="/contacts/all" class="add-link">← Назад к контактам</a>
</div>
</body>
</html>
