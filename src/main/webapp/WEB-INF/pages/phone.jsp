ё<%@ page contentType="text/html;charset=UTF-8" language="java" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <html>
 <head>
     <title>Телефон</title>
     <style>
         <%@ include file="style.css" %>
     </style>
 </head>
 <body>
     <div class="form-container">
         <c:choose>
             <c:when test="${empty phone.id}">
                 <h1>➕ Добавить номер телефона</h1>
             </c:when>
             <c:otherwise>
                 <h1>✏️ Редактировать номер телефона</h1>
             </c:otherwise>
         </c:choose>

         <form action="/phones/save" method="post">
             <c:if test="${not empty phone.id}">
                 <input type="hidden" name="id" value="${phone.id}">
             </c:if>

             <!-- ЭТО ПОЛЕ ДОЛЖНО БЫТЬ ВСЕГДА! -->
             <input type="hidden" name="personId" value="${personId}">

             <label>Номер телефона:</label>
             <input type="text" name="number" value="${phone.number}" required>

             <label>Тип телефона:</label>
             <select name="numberType">
                 <option value="MOBILE" ${phone.numberType == 'MOBILE' ? 'selected' : ''}>📱 Мобильный</option>
                 <option value="HOME" ${phone.numberType == 'HOME' ? 'selected' : ''}>🏠 Домашний</option>
                 <option value="WORK" ${phone.numberType == 'WORK' ? 'selected' : ''}>💼 Рабочий</option>
                 <option value="FAX" ${phone.numberType == 'FAX' ? 'selected' : ''}>📠 Факс</option>
             </select>

             <label>Описание:</label>
             <textarea name="description" rows="3" maxlength="500" style="width: 100%; padding: 12px; margin: 8px 0 20px 0; border: 2px solid #ddd; border-radius: 6px;">${phone.description}</textarea>
             <small>Максимум 500 символов</small>

             <div class="button-group">
                 <button type="submit" class="save-btn">💾 Сохранить</button>
                 <a href="/phones/${personId}" class="cancel-link">❌ Отмена</a>
             </div>
         </form>
     </div>
 </body>
 </html>