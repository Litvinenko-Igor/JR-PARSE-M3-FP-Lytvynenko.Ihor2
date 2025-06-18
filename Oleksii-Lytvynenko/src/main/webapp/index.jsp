<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Dungeon Quest</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div>
    <h1 id="header" class="header">
        Побег из подземелья
    </h1>
</div>
<div class="story-box">
    <h2 class="story-container">Вы просыпаетесь в темной комнате с кандалами на ногах. Ваша цель — выбраться из подземелья.</h2>
</div>
<div class="button-container">
    <form action="game" method="get">
        <button class="submit-button" type="submit">Начать игру</button>
    </form>
</div>
</body>
</html>
