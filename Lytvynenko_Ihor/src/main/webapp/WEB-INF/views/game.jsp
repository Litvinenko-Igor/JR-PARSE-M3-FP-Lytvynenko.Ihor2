<%@ page import="com.java.classes.QuestNode, com.java.classes.GameSession" %>
<%--@elvariable id="questNode" type="com.java.classes.QuestNode"--%>
<%--@elvariable id="gameSession" type="com.java.classes.GameSession"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Проєкт "НЛО"</title>
    <link rel="stylesheet" href="static/siteGame.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&family=Orbitron:wght@700&display=swap" rel="stylesheet">

</head>
<body>
<div class="background-container">
    <div class="ufo">
        <div class="ufo-dome"></div><div class="ufo-body"></div>
    </div>
</div>

<div class="container">
    <div class="main-content">
        <h1>${questNode.questionText}</h1>
        <div class="button-group">
            <c:choose>
                <c:when test="${questNode.losingNode}">
                    <a href="${pageContext.request.contextPath}" class="simple-btn secondary">Почати гру заново</a>
                </c:when>
                <c:otherwise>
                    <form action="${pageContext.request.contextPath}/game" method="post" style="width: 100%;">
                        <c:forEach var="choice" items="${questNode.choices}">
                            <button class="simple-btn" type="submit" name="choiceId" value="${choice.key}">${choice.value}</button>
                        </c:forEach>
                    </form>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <aside class="stats-panel">
        <h2>📊 Статистика</h2>
        <p>Гравець: <span>${gameSession.name}</span></p>
        <p>Номер Глави: <span>${gameSession.currentNodeId}</span></p>
        <p>Кількість ігор: <span>${gameSession.count}</span></p>
    </aside>
</div>
</body>
</html>
