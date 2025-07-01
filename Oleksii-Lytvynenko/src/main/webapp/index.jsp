<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Dungeon Quest</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="icon" href="../img/logo.jpg">
</head>
<body>
<div>
    <h1 id="header" class="header">
        Dungeon Quest
    </h1>
</div>
<div class="story-box">
    <h2 class="story-container">Ти прокидаєшся в темній кімнаті з кайданками на ногах. Твоя мета — втекти з цього підземелля.</h2>
</div>
<div class="button-container">
    <form action="game" method="get">
        <button class="submit-button" type="submit">Почати гру</button>
    </form>
</div>
</body>
</html>
