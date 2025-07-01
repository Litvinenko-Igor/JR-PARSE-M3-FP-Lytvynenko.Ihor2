<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="quest.util.GameLogic" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    GameLogic logic = (GameLogic) session.getAttribute("logic");
    String[] options = logic.getOptions();
    request.setAttribute("question", logic.getQuestion());
    request.setAttribute("options", options);
%>
<html><body>
<h3>${question}</h3>
<form method="post" action="quest">
    <c:forEach var="opt" items="${options}">
        <button type="submit" name="answer" value="${opt}">${opt}</button><br/>
    </c:forEach>
</form>
</body></html>
