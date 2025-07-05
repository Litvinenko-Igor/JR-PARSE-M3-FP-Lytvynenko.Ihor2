<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Загублений артефакт - Ласкаво просимо!</title>
    <style>
        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; margin: 2em; background-color: #f0f2f5; color: #333; line-height: 1.6; }
        .container { background-color: #ffffff; padding: 2.5em; border-radius: 10px; box-shadow: 0 4px 12px rgba(0,0,0,0.1); max-width: 650px; margin: 2em auto; text-align: center; }
        h1 { color: #2c3e50; margin-bottom: 0.8em; font-size: 2em; }
        p { margin-bottom: 1.2em; font-size: 1.1em; color: #555; }
        .games-played { font-style: italic; color: #7f8c8d; margin-top: 1.5em; font-size: 0.95em; }
        form { margin-top: 2em; display: flex; flex-direction: column; align-items: center; }
        input[type="text"] {
            width: 80%;
            max-width: 300px;
            padding: 0.8em 1em;
            margin-bottom: 1em;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 1em;
            box-sizing: border-box;
        }
        input[type="submit"] {
            padding: 0.9em 2em;
            background-color: #3498db;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 1.1em;
            transition: background-color 0.3s ease;
        }
        input[type="submit"]:hover { background-color: #2980b9; }
    </style>
</head>
<body>
<div class="container">
    <h1>Ласкаво просимо, вцілілий!</h1>
    <p>У далекому майбутньому людство освоює міжзоряні простори. Ви — член екіпажу наукової космічної станції "Оріон", що дрейфує на орбіті невідомої планети. Станція, яка мала стати маяком прогресу, раптово замовкла. Системи життєзабезпечення відмовляють, освітлення гасне, а моторошні звуки лунають з глибин її металевих коридорів. Зв'язок із Землею втрачено, і ніхто не знає, що сталося.</p>
    <p>Ви опинилися у пастці, і час спливає. Ваше завдання — знайти спосіб втекти зі станції "Оріон" до того, як вона перетвориться на вашу могилу. Чи зможете ви розгадати таємниці, що охопили цей корабель, подолати несправності та уникнути невідомої загрози? Або ж ви станете ще однією жертвою, що назавжди загубиться в безмежній космічній безодні?</p>

    <p class="games-played">Загалом зіграно ігор: <c:out value="${sessionScope.gamesPlayed}" default="0"/></p>

    <form action="${pageContext.request.contextPath}/start" method="post">
        <label for="playerName">Введіть ваше ім'я:</label>
        <input type="text" id="playerName" name="playerName" placeholder="Ваше ім'я героя" required>
        <input type="submit" value="Розпочати пригоду">
    </form>
</div>
</body>
</html>