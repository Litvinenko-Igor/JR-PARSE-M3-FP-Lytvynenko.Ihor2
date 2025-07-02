
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Stats</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background: linear-gradient(120deg, #84fab0 0%, #8fd3f4 100%);
            height: 100vh;
            margin: 0;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }

        .container {
            background: rgba(255, 255, 255, 0.9);
            padding: 2rem 3rem;
            border-radius: 15px;
            box-shadow: 0 8px 24px rgba(0, 0, 0, 0.1);
            text-align: center;
            max-width: 90%;
            width: 500px;
        }

        h2 {
            font-size: 2.5rem;
            margin-bottom: 1.5rem;
            text-transform: uppercase;
            letter-spacing: 2px;
        }

        .victory {
            color: #2ecc71;
            text-shadow: 0 2px 4px rgba(46, 204, 113, 0.2);
        }

        .defeat {
            color: #e74c3c;
            text-shadow: 0 2px 4px rgba(231, 76, 60, 0.2);
        }

        .stats {
            background: rgba(255, 255, 255, 0.8);
            padding: 1.5rem;
            border-radius: 12px;
            margin: 1.5rem 0;
            text-align: left;
        }

        .stats h3 {
            color: #2c3e50;
            margin-bottom: 1.2rem;
            font-size: 1.4rem;
            text-align: center;
            border-bottom: 2px solid #e0e0e0;
            padding-bottom: 0.5rem;
        }

        .stats p {
            margin: 0.8rem 0;
            color: #34495e;
            font-size: 1.1rem;
            display: flex;
            justify-content: space-between;
            padding: 0.3rem 1rem;
            border-radius: 6px;
            transition: background-color 0.3s ease;
        }

        .stats p:hover {
            background-color: rgba(132, 250, 176, 0.1);
        }

        input[type="submit"] {
            padding: 0.8rem 2rem;
            background: linear-gradient(to right, #84fab0, #8fd3f4);
            border: none;
            border-radius: 8px;
            color: white;
            font-size: 1.1rem;
            cursor: pointer;
            transition: all 0.3s ease;
            width: 100%;
            margin-top: 1rem;
        }

        input[type="submit"]:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(132, 250, 176, 0.4);
        }

        input[type="submit"]:active {
            transform: translateY(0);
        }

        @media (max-width: 600px) {
            .container {
                padding: 1.5rem;
                width: 90%;
            }

            h2 {
                font-size: 2rem;
            }

            .stats p {
                font-size: 1rem;
                padding: 0.2rem 0.5rem;
            }

            input[type="submit"] {
                padding: 0.8rem 1.5rem;
                font-size: 1rem;
            }
        }
    </style>
</head>
<body>
<div class="container">
    <h2 class="${result == 'Перемога' ? 'victory' : 'defeat'}">${result}!</h2>

    <div class="stats">
        <h3>Статистика гравця ${playerStats.playerName}</h3>
        <p><span>Всього ігор:</span> <span>${playerStats.gamesPlayed}</span></p>
        <p><span>Перемог:</span> <span>${playerStats.victories}</span></p>
        <p><span>Поразок:</span> <span>${playerStats.defeats}</span></p>
        <p><span>Поточна серія:</span> <span>${playerStats.currentStreak}</span></p>
        <p><span>Найкраща серія:</span> <span>${playerStats.bestStreak}</span></p>
        <p><span>Відсоток перемог:</span> <span><fmt:formatNumber value="${playerStats.winRate}" maxFractionDigits="1"/>%</span></p>
    </div>

    <form action="quest" method="post">
        <input type="hidden" name="restart" value="true">
        <input type="submit" value="Грати знову">
    </form>
</div>
</body>
</html>