<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>${title}</title>
</head>
<body>
    <h2>${story}</h2>

    <c:if test="${not empty chosenStory}">
        <h3>${chosenTitle}</h3>
        <p>${chosenStory}</p>
        <form method="get" action="game">
            <input type="hidden" name="next" value="${decisionKey}" />
            <button type="submit">Продолжить</button>
        </form>
    </c:if>

    <c:if test="${empty chosenStory}">
        <h3>${decision.prompt}</h3>
        <ul>
            <c:forEach var="option" items="${decision.options}" varStatus="status">
                <li>
                    <form method="get" action="game">
                        <input type="hidden" name="choiceIndex" value="${status.index}" />
                        <input type="hidden" name="next" value="${option.next}" />
                        <button type="submit">${option.title}</button>
                    </form>
                </li>
            </c:forEach>
        </ul>
    </c:if>

</body>
</html>
