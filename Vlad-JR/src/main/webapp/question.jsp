<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="ua">
<head>
    <meta charset="UTF-8">
    <title>Текстовий квест</title>
    <style>
        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            padding: 0;
            font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(120deg, #2c3e50, #4ca1af);
            color: #cccccc;
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .question-box {
            background-color: rgba(46, 46, 46, 0.85);
            padding: 30px 40px;
            border-radius: 12px;
            box-shadow: 0 0 10px rgba(0,0,0,0.5);
            max-width: 600px;
            width: 100%;
        }

        .question {
            font-size: 1.5em;
            margin-bottom: 20px;
            text-shadow: 1px 1px 4px rgba(0,0,0,0.5);
        }

        .answer {
            margin-bottom: 15px;
        }

        .answer input[type="radio"] {
            margin-right: 10px;
            accent-color: #4ca1af;
            cursor: pointer;
        }

        .answer label {
            font-size: 1.1em;
        }

        .submit-btn {
            padding: 12px 24px;
            font-size: 1.2em;
            border: none;
            border-radius: 8px;
            background-color: #2e2e2e;
            color: #fff;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .submit-btn:hover {
            background-color: #3a3a3a;
        }
    </style>
</head>
<body>

<div class="question-box">
    <form method="post" action="game">
        <div class="question">
            <c:out value="${sessionScope.question.getText()}" />
        </div>

        <div class="answer">
            <input type="radio" name="answer" value="0" id="opt1" required>
            <label for="opt1"><c:out value="${sessionScope.question.getAnswerText(0)}" /></label>
        </div>

        <div class="answer">
            <input type="radio" name="answer" value="1" id="opt2">
            <label for="opt2"><c:out value="${sessionScope.question.getAnswerText(1)}" /></label>
        </div>

        <button type="submit" class="submit-btn">Підтвердити</button>
    </form>
</div>

</body>
</html>
