<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html><body>
<h2>Результат: ${result}</h2>
<form method="post" action="quest">
    <input type="hidden" name="restart" value="true"/>
    <input type="submit" value="Грати знову"/>
</form>
</body></html>
