<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Викторина: Знаток мира</title>
    <link rel="stylesheet" href="css/style.css">
    <style>
        body {
            transition: background-image 0.5s ease-in-out;
        }

        .bg-africa { background-image: url('/img/continents/africa.jpg'); }
        .bg-asia { background-image: url('/img/continents/asia.jpg'); }
        .bg-europe { background-image: url('/img/continents/europe.jpg'); }
        .bg-northamerica { background-image: url('/img/continents/north_america.jpg'); }
        .bg-southamerica { background-image: url('/img/continents/south_america.jpg'); }
        .bg-oceania { background-image: url('/img/continents/oceania.jpg'); }
        .bg-antarctica { background-image: url('/img/continents/antarctica.jpg'); }
        .bg-default { background-image: url('/img/3d-rendering-sestiugol-noi-tekstury-fona(1).jpg'); }

        .quiz-container {
            max-width: 800px;
            margin: 5vh auto;
            padding: 40px;
            border: 1px solid #555;
            border-radius: 15px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.5);
            background-size: cover;
            background-position: center;
            position: relative;
            z-index: 1;
        }

        .quiz-container::before {
            content: '';
            position: absolute;
            top: 0; left: 0; right: 0; bottom: 0;
            background: rgba(0, 0, 0, 0.7);
            border-radius: 15px;
            z-index: -1;
        }

        .progress-bar {
            width: 100%;
            background-color: #555;
            border-radius: 5px;
            margin-bottom: 20px;
        }
        .progress {
            width: ${100 * (step) / totalSteps}%;
            height: 20px;
            background-color: #4CAF50;
            border-radius: 5px;
            text-align: center;
            line-height: 20px;
            color: white;
            transition: width 0.5s ease-in-out;
        }
        .question-text {
            font-size: 24px;
            font-weight: bold;
            margin-bottom: 20px;
            color: white; /* Явный цвет текста вопроса */
        }
        .options label {
            display: block;
            margin: 10px 0;
            padding: 15px;
            font-size: 20px;
            cursor: pointer;
            background-color: rgba(255, 255, 255, 0.1);
            border-radius: 8px;
            transition: background-color 0.2s;
            color: #ddd; /* Явный цвет текста опций */
        }
        .options label:hover {
            background-color: rgba(255, 255, 255, 0.2);
        }
        .submit-button {
            width: 100%;
            padding: 15px;
            margin-top: 20px;
            font-size: 20px;
            font-weight: bold;
            cursor: pointer;
            border: none;
            border-radius: 5px;
            background-color: #2a6496;
            color: white;
            transition: background-color 0.2s;
        }
        .submit-button:hover {
            background-color: #3b8bba;
        }
    </style>
</head>
<body class="${not empty sessionScope.backgroundClass ? sessionScope.backgroundClass : 'bg-default'}">

<div class="quiz-container"
     style="<c:if test='${not empty currentQuestion.imageUrl}'>background-image: url('${currentQuestion.imageUrl}');</c:if>">

    <h2>Вопрос ${step } из ${totalSteps}</h2>
    <div class="progress-bar">
        <div class="progress">${step } / ${totalSteps}</div>
    </div>

    <hr style="border-color: #444;">

    <form method="post" action="game-knowledge">
        <fieldset style="border: none; padding: 0;">
            <legend class="question-text">${currentQuestion.text}</legend>

            <div class="options">
                <c:forEach var="opt" items="${currentQuestion.options}">
                    <label>
                        <input type="checkbox" name="q${currentQuestion.id}" value="${opt}"> ${opt}
                    </label>
                </c:forEach>
            </div>
        </fieldset>

        <button type="submit" class="submit-button">Відповісти</button>
    </form>

</div>
</body>
</html>
