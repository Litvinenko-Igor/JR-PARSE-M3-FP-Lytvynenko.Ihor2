<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Проклятий будинок</title>
  <style>
    body { font-family: Arial, sans-serif; background-color: #1a1a1a; color: #fff; text-align: center; padding: 50px; }
    .container { max-width: 600px; margin: auto; }
    button { padding: 10px; margin: 10px; }
  </style>
</head>
<body>
<div class="container">
  <h1>Проклятий будинок</h1>
  <p>Гравець: ${sessionScope.playerName}</p>
  <p>Кількість спроб: ${sessionScope.gamesPlayed}</p>
  <p>Кількість перемог: ${sessionScope.wins}</p>

  <c:choose>
    <c:when test="${empty gameState}">
      <p>Помилка: стан гри не визначено!</p>
    </c:when>
    <c:otherwise>
      <p>${gameState.description}</p>
      <c:if test="${gameState.isGameOver}">
        <p>${gameState.result}</p>
        <form action="game" method="post">
          <input type="hidden" name="action" value="restart">
          <button type="submit">Спробувати ще раз</button>
        </form>
      </c:if>
      <c:if test="${not gameState.isGameOver}">
        <form action="game" method="post">
          <c:forEach var="choice" items="${gameState.choices}">
            <button type="submit" name="choice" value="${choice.key}">${choice.value}</button>
          </c:forEach>
        </form>
      </c:if>
    </c:otherwise>
  </c:choose>
</div>
</body>
</html>