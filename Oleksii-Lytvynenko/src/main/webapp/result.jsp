<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Результат</title>
</head>
<body>
    <c:choose>
        <c:when test="${outcome == 'dead'}">
            <h2>Вы погибли.</h2>
            <p>Игра окончена. Попыток: ${attempts}</p>
        </c:when>
        <c:when test="${outcome == 'win'}">
            <h2>Поздравляем!</h2>
            <p>Вы выбрались из подземелья живым. Победа!</p>
            <p>Всего попыток: ${attempts}</p>
        </c:when>
    </c:choose>

    <form action="game" method="get">
        <button type="submit">Начать заново</button>
    </form>
</body>
</html>
