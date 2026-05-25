<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить телефон</title>
    <style>
        <%@ include file="style.css" %>
    </style>
</head>
    <body>
        <div class="form-container">
            <h1>➕ Добавить номер телефона</h1>
            <form action="/phones/save" method="post">
                <input type="hidden" name="personId" value="${personId}">

                <label>Номер телефона:</label>
                <input type="text" name="number" placeholder="+375-29-123-45-67" required>

                <label>Тип телефона:</label>
                <select name="type">
                    <option value="">-- Выберите тип --</option>
                    <option value="MOBILE">📱 Мобильный</option>
                    <option value="HOME">🏠 Домашний</option>
                    <option value="WORK">💼 Рабочий</option>
                </select>

                <div class="button-group">
                    <button type="submit" class="save-btn">💾 Сохранить</button>
                    <a href="/phones/${personId}" class="cancel-link">❌ Отмена</a>
                </div>
            </form>
        </div>
    </body>
</html>