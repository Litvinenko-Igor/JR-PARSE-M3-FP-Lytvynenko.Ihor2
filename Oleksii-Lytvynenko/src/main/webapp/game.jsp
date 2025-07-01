<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>${title}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="icon" href="img/logo.jpg">
</head>
<body>
<div class="story-box">
<c:if test="${not empty chosenStory}">
    <h3 class="game-title">${chosenTitle}</h3>
    <p class="game-story">${chosenStory}</p>
    <form method="get" action="game">
        <input type="hidden" name="next" value="${decisionKey}"/>
        <button class="submit-button" type="submit">Продовжити</button>
    </form>
</c:if>

<c:if test="${empty chosenStory}">
    <h3 class="decision-prompt">${decision.prompt}</h3>
    <ul>
        <c:forEach var="option" items="${decision.options}" varStatus="status">
                <form method="get" action="game">
                    <input type="hidden" name="choiceIndex" value="${status.index}"/>
                    <input type="hidden" name="next" value="${option.next}"/>
                    <button class="submit-button" type="submit">${option.title}</button>
                </form>
        </c:forEach>
    </ul>
</c:if>
</div>
</body>
</html>
