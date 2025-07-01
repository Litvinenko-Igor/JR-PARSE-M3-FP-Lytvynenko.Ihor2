<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
</head>
<body>
<h2>Введіть ім’я гравця:</h2>
<form method="post" action="quest">
    <input type="text" name="playerName" required/>
    <input type="submit" value="Почати гру"/>
</form>
</body></html>
