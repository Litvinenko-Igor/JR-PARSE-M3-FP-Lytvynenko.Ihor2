<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>UFO</title>
</head>
<body>
<h3>${question}</h3>

<form method="post" action="quest">
    <c:forEach var="opt" items="${options}">
        <button type="submit" name="answer" value="${opt}">${opt}</button><br/>
    </c:forEach>
</form>
</body>
</html>
