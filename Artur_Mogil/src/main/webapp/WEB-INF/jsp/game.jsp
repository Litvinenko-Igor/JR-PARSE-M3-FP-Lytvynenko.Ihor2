<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Загублений артефакт - <c:if test="${requestScope.isGameOver}">Кінець гри</c:if><c:if test="${not requestScope.isGameOver}">Пригода</c:if></title>
  <style>
    body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; margin: 2em; background-color: #f0f2f5; color: #333; line-height: 1.6; }
    .container { background-color: #ffffff; padding: 2.5em; border-radius: 10px; box-shadow: 0 4px 12px rgba(0,0,0,0.1); max-width: 650px; margin: 2em auto; }
    h2 { color: #2c3e50; margin-bottom: 1em; font-size: 1.8em; text-align: center; }
    h1 { margin-bottom: 0.8em; font-size: 2.2em; }
    h1.win { color: #27ae60; }
    h1.lose { color: #e74c3c; }
    p { margin-bottom: 1.2em; font-size: 1.1em; color: #555; }
    .scenario-text, .result-text {
      background-color: #ecf0f1;
      padding: 1.5em;
      border-radius: 8px;
      margin-bottom: 2em;
      text-align: justify;
    }
    .scenario-text p, .result-text p { margin: 0; font-size: 1.1em; color: #444; }
    .choices { text-align: center; }
    .choices button {
      display: block;
      width: 100%;
      padding: 1em 1.5em;
      margin-bottom: 0.8em;
      background-color: #2ecc71;
      color: white;
      border: none;
      border-radius: 5px;
      cursor: pointer;
      font-size: 1.1em;
      transition: background-color 0.3s ease, transform 0.2s ease;
    }
    .choices button:hover {
      background-color: #27ae60;
      transform: translateY(-2px);
    }
    .player-info { margin-top: 2.5em; font-style: italic; color: #7f8c8d; text-align: center; font-size: 0.95em; }
    .button-restart {
      display: inline-block;
      padding: 1em 2em;
      margin-top: 1.5em;
      background-color: #3498db;
      color: white;
      text-decoration: none;
      border-radius: 5px;
      font-size: 1.1em;
      transition: background-color 0.3s ease, transform 0.2s ease;
    }
    .button-restart:hover {
      background-color: #2980b9;
      transform: translateY(-2px);
    }
  </style>
</head>
<body>
<div class="container">
  <c:set var="game" value="${sessionScope.questGame}" />
  <c:set var="scenario" value="${requestScope.currentScenario}" />
  <c:set var="isGameOver" value="${requestScope.isGameOver}" />

  <c:if test="${empty game || empty scenario}">
    <p>Сталася помилка, або гра не розпочата. Будь ласка, <a href="${pageContext.request.contextPath}/start">розпочніть гру заново</a>.</p>
  </c:if>

  <c:if test="${not empty game && not empty scenario}">
    <c:choose>
      <c:when test="${isGameOver}">
        <c:if test="${game.win}">
          <h1 class="win">Перемога!</h1>
          <div class="result-text">
            <p><c:out value="${scenario.text}"/></p>
          </div>
          <p>Вітаємо, <c:out value="${game.playerName}"/>! Ви врятувались!</p>
        </c:if>
        <c:if test="${not game.win}">
          <h1 class="lose">Поразка!</h1>
          <div class="result-text">
            <p><c:out value="${scenario.text}"/></p>
          </div>
          <p>На жаль, <c:out value="${game.playerName}"/>, ваша пригода завершилася невдачею. Але не засмучуйтеся, спробуйте ще раз!</p>
        </c:if>
        <a href="${pageContext.request.contextPath}/start" class="button-restart">Почати нову гру</a>
      </c:when>
      <c:otherwise>
        <h2>Ваша пригода, <c:out value="${game.playerName}"/>!</h2>
        <div class="scenario-text">
          <p><c:out value="${scenario.text}"/></p>
        </div>

        <div class="choices">
          <form action="${pageContext.request.contextPath}/game" method="post">
            <c:if test="${not empty scenario.choice1}">
              <button type="submit" name="choice" value="${scenario.choice1.id}"><c:out value="${scenario.choice1.description}"/></button>
            </c:if>
            <c:if test="${not empty scenario.choice2}">
              <button type="submit" name="choice" value="${scenario.choice2.id}"><c:out value="${scenario.choice2.description}"/></button>
            </c:if>
          </form>
        </div>
      </c:otherwise>
    </c:choose>

    <div class="player-info">
      <p>Загалом зіграно ігор: <c:out value="${sessionScope.gamesPlayed}"/></p>
    </div>
  </c:if>
</div>
</body>
</html>