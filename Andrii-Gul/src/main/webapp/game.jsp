<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Лесной квест</title>
    <link rel="stylesheet" href="css/style.css">
    <style>
        .quest-container { max-width: 800px; margin: 2vh auto; padding: 30px; background-color: rgba(0, 0, 0, 0.75); border: 1px solid #555; border-radius: 15px; box-shadow: 0 0 20px rgba(0, 0, 0, 0.6); color: #eee; }
        .quest-image { width: 100%; max-height: 400px; object-fit: cover; border-radius: 10px; border: 2px solid #444; margin-bottom: 20px; }
        .quest-text { font-size: 20px; line-height: 1.6; margin-bottom: 30px; }
        .quest-options button { display: block; width: 100%; padding: 15px; margin-bottom: 10px; font-size: 18px; text-align: left; cursor: pointer; border: 1px solid #666; border-radius: 8px; background-color: #333; color: #ddd; transition: all 0.2s; }
        .quest-options button:hover { background-color: #4CAF50; color: white; border-color: #4CAF50; }
        .player-stats { position: fixed; top: 20px; right: 20px; padding: 15px; background-color: rgba(0,0,0,0.8); border: 1px solid #777; border-radius: 10px; z-index: 100; }
        .hp-bar-container { width: 150px; height: 20px; background-color: #555; border-radius: 5px; border: 1px solid #888; }
        .hp-bar { height: 100%; background-color: #d9534f; border-radius: 4px; transition: width 0.5s ease-in-out; }
        .final-screen { text-align: center; }
        .final-screen h2 { font-size: 3em; }
    </style>
</head>
<body>
<div class="player-stats">
    <strong>${sessionScope.player.playerName}</strong><br>
    Здоровье (HP): ${playerHp}
    <div class="hp-bar-container">
        <div class="hp-bar" style="width: ${playerHp}%;"></div>
    </div>
</div>

<div class="quest-container">
    <c:if test="${not empty currentStep.imageUrl}">
        <img class="quest-image" src="${currentStep.imageUrl}" alt="Иллюстрация квеста">
    </c:if>

    <p class="quest-text">${currentStep.text}</p>

    <hr style="border-color: #444;">

    <c:choose>
        <c:when test="${currentStep.terminal}">
            <div class="final-screen">
                <c:if test="${currentStep.win}">
                    <h2>ПЕРЕМОГА!</h2>
                </c:if>
                <form method="post" action="game">
                    <button type="submit" name="restart" value="true" class="game-button">Зіграти ще</button>
                </form>
                <a href="selectGame" class="logout-link" style="font-size: 18px;">Вибрати іншу гру</a>
            </div>
        </c:when>

        <c:otherwise>
            <form method="post" action="game" class="quest-options">
                <c:forEach var="option" items="${currentStep.options}" varStatus="loop">
                    <button type="submit" name="optionIndex" value="${loop.index}">${option.text}</button>
                </c:forEach>
            </form>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
