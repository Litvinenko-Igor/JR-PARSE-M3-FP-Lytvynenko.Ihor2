<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Результат</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <c:choose>
        <c:when test="${outcome == 'dead'}">
            <h2 class="dead">Ви померли!</h2>
            <p>Гру закінчено. Спроби: ${sessionScope.attempts}</p>
        </c:when>
        <c:when test="${outcome == 'win'}">
            <h2 class="win">Вітаю !</h2>
            <p>Ви вийшли з підземелля живим. Перемога !</p>
            <p>Всього спроб: ${sessionScope.attempts}</p>
        </c:when>
    </c:choose>

    <form action="game" method="get">
        <button class="submit-button" type="submit">Почати знову</button>
    </form>
</body>
</html>
