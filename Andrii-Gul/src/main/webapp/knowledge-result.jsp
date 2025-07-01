<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Результаты викторины</title>
    <link rel="stylesheet" href="css/style.css">
    <style>
        .bg-africa { background-image: url('/img/continents/africa.jpg'); }
        .bg-asia { background-image: url('/img/continents/asia.jpg'); }
        .bg-europe { background-image: url('/img/continents/europe.jpg'); }
        .bg-northamerica { background-image: url('/img/continents/north_america.jpg'); }
        .bg-southamerica { background-image: url('/img/continents/south_america.jpg'); }
        .bg-oceania { background-image: url('/img/continents/oceania.jpg'); }
        .bg-antarctica { background-image: url('/img/continents/antarctica.jpg'); }
        .bg-default { background-image: url('/img/3d-rendering-sestiugol-noi-tekstury-fona(1).jpg'); }

        body {
            transition: background-image 0.5s ease-in-out;
        }

        .errors-container {
            margin-top: 30px;
            padding: 20px;
            background-color: rgba(255, 0, 0, 0.1);
            border: 1px solid #d9534f;
            border-radius: 8px;
            text-align: left;
        }
        .error-question {
            margin-bottom: 20px;
            padding-bottom: 15px;
            border-bottom: 1px dashed #777;
        }
        .error-question:last-child {
            border-bottom: none;
            margin-bottom: 0;
            padding-bottom: 0;
        }
        .question-text {
            font-weight: bold;
            font-size: 1.1em;
            color: #fdd;
        }
        .correct-answer {
            color: #9dff9d;
            font-weight: bold;
        }
    </style>
</head>
<body class="${not empty sessionScope.backgroundClass ? sessionScope.backgroundClass : 'bg-default'}">

<div class="game-select-container">
    <h2>Вікторина завершена!</h2>
    <p>Гравець: <b>${sessionScope.player.playerName}</b></p>
    <hr style="border-color: #444;">
    <h3>Загальна статистика:</h3>
    <p>Всього очків за всі ігри: <b>${sessionScope.player.score}</b></p>
    <p>Всього ігор зіграно: <b>${sessionScope.player.gamesPlayed}</b></p>

    <c:if test="${not empty requestScope.incorrectQuestions}">
        <div class="errors-container">
            <h3>Робота над помилками:</h3>
            <c:forEach var="q" items="${requestScope.incorrectQuestions}">
                <div class="error-question">
                    <p class="question-text">${q.text}</p>
                    <p>Правильна відповідь:
                        <span class="correct-answer">
                            <c:forEach var="answer" items="${q.correctAnswers}" varStatus="loop">
                                ${answer}${!loop.last ? ', ' : ''}
                            </c:forEach>
                        </span>
                    </p>
                </div>
            </c:forEach>
        </div>
    </c:if>

    <div style="margin-top: 30px;">
        <a href="knowledgeStart.jsp" class="logout-link">Зіграти ще раз</a><br><br>
        <a href="selectGame" class="logout-link">Вибрати іншу игру</a><br><br>
        <a href="logout" class="logout-link">Почати з іншим гравцем</a>
    </div>
</div>
</body>
</html>
