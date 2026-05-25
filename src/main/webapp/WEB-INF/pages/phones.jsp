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
            <h1>📞 Телефоны: ${person.firstName} ${person.lastName}</h1>

            <c:choose>
                <c:when test="${empty phones}">
                    <p>❌ Нет добавленных телефонов</p>
                </c:when>
                <c:otherwise>
                    <table>
                        <thead>
                            <tr>
                                <th>Номер телефона</th>
                                <th>Тип</th>
                                <th>Действия</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${phones}" var="phone" varStatus="status">
                                <tr>
                                    <td>
                                        ${phone.number}
                                        <c:if test="${status.first}">
                                            <span style="color: green;"> (Основной)</span>
                                        </c:if>
                                    </td>
                                    <td>${phone.type != null ? phone.type : 'не указан'}</td>
                                    <td>
                                        <a href="/phones/edit/${phone.id}" class="edit-btn">✏️ Редактировать</a>
                                        <a href="/phones/delete/${phone.id}"
                                           onclick="return confirm('Удалить номер ${phone.number}?')"
                                           class="delete-btn">❌ Удалить</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:otherwise>
            </c:choose>

            <br/>
            <a href="/phones/add/${person.id}" class="add-link">➕ Добавить номер</a>
            <br/><br/>
            <a href="/persons/all" class="add-link">← Назад к контактам</a>
        </div>
    </body>
</html>