<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Результат гри</title>
    <style>
        .stats {
            margin: 20px;
            padding: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .victory { color: green; }
        .defeat { color: red; }
    </style>
</head>
<body>
    <h2 class="${result == 'Перемога' ? 'victory' : 'defeat'}">${result}!</h2>
    
    <div class="stats">
        <h3>Статистика гравця ${playerStats.playerName}</h3>
        <p>Всього ігор: ${playerStats.gamesPlayed}</p>
        <p>Перемог: ${playerStats.victories}</p>
        <p>Поразок: ${playerStats.defeats}</p>
        <p>Поточна серія: ${playerStats.currentStreak}</p>
        <p>Найкраща серія: ${playerStats.bestStreak}</p>
        <p>Відсоток перемог: <fmt:formatNumber value="${playerStats.winRate}" maxFractionDigits="1"/>%</p>
    </div>

    <form action="quest" method="post">
        <input type="hidden" name="restart" value="true">
        <input type="submit" value="Грати знову">
    </form>
</body>
</html>