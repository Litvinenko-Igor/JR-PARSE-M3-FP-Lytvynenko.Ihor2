<%-- src/main/webapp/index.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Завантаження гри...</title>
    <meta http-equiv="refresh" content="0;url=${pageContext.request.contextPath}/start">
</head>
<body>
<p>Завантаження гри... Якщо перенаправлення не відбулося, натисніть <a href="${pageContext.request.contextPath}/start">тут</a>.</p>
</body>
</html>