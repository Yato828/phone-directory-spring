<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
            <div class="search-container">
                    <form action="/persons/search" method="get" class="search-form">
                        <input type="text"
                               name="query"
                               class="search-input"
                               placeholder="🔍 Поиск"
                               value="${param.query}">
                        <button type="submit" class="search-button">🔍 Найти</button>
                        <a href="/persons/all" class="clear-button">✖️ Очистить</a>
                    </form>
                </div>
            <c:choose>
                <c:when test="${empty persons}">
                    <div class="empty-message">
                        <p>Нет сохраненных контактов</p>
                        <a href="/persons/add" class="add-link">➕ Добавить первый контакт</a>
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
                                <th>Телефоны</th>
                                <th>Редактировать</th>
                                <th>Удалить</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${persons}" var="person">
                                <tr>
                                    <td>${person.firstName}</td>
                                    <td>${person.lastName}</td>
                                    <td>${not empty person.middleName ? person.middleName : '-'}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${not empty person.phoneNumbers}">
                                                ${person.phoneNumbers[0].number}
                                            </c:when>
                                            <c:otherwise>-</c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${not empty person.birth}">
                                                <fmt:parseDate value="${person.birth}" pattern="yyyy-MM-dd" var="parsedDate" />
                                                <fmt:formatDate value="${parsedDate}" pattern="dd.MM.yyyy" />
                                            </c:when>
                                            <c:otherwise>-</c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <a href="/phones/${person.id}" class="edit-btn">📞 Телефоны</a>
                                    </td>
                                    <td>
                                        <form action="/persons/edit" method="get" style="display:inline;">
                                            <input type="hidden" name="id" value="${person.id}">
                                            <button type="submit" class="edit-btn">✏️ Редактировать</button>
                                        </form>
                                    </td>
                                    <td>
                                        <form action="/persons/delete" method="post" style="display:inline;">
                                            <input type="hidden" name="id" value="${person.id}">
                                            <button type="submit" class="delete-btn" onclick="return confirm('Удалить контакт ${person.firstName} ${person.lastName}?')">❌ Удалить</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <br/>
                    <a href="/persons/add" class="add-link">➕ Добавить новый контакт</a>
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>