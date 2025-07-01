<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="css/style.css">
    <title>Ласкаво просимо до квесту</title>
</head>
<body>
<div class="header">
    <h1>Привіт, мандрівнику!</h1>
    <h2>Твої пригоди починаються тут. Обери свій шлях і нехай він приведе тебе до перемоги!</h2>

    <form method="post" action="welcome">
        <label for="playerName">Назви себе, щоб я міг вести лік твоїм подвигам:</label>
        <input type="text" id="playerName" name="playerName" required placeholder="Твоє ім'я">
        <button type="submit">Почати пригоду</button>
    </form>
</div>
</body>
</html>
