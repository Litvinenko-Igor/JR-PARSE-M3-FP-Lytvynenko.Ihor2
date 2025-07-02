<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="ua">
<head>
    <meta charset="UTF-8">
    <title>Вхід</title>
    <style>
        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            padding: 0;
            font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(120deg, #2c3e50, #4ca1af);
            color: #cccccc;
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            flex-direction: column;
        }

        .login-box {
            background-color: rgba(46, 46, 46, 0.85);
            padding: 30px 40px;
            border-radius: 12px;
            box-shadow: 0 0 10px rgba(0,0,0,0.5);
            width: 100%;
            max-width: 400px;
        }

        .login-title {
            font-size: 1.8em;
            margin-bottom: 20px;
            text-align: center;
            text-shadow: 1px 1px 4px rgba(0,0,0,0.5);
        }

        label {
            display: block;
            margin-bottom: 5px;
            color: #eee;
        }

        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: none;
            border-radius: 6px;
            background-color: #2e2e2e;
            color: #fff;
            font-size: 1em;
        }

        input[type="text"]::placeholder,
        input[type="password"]::placeholder {
            color: #aaa;
        }

        .login-btn {
            width: 100%;
            padding: 12px;
            font-size: 1.1em;
            border: none;
            border-radius: 8px;
            background-color: #2e2e2e;
            color: #fff;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .login-btn:hover {
            background-color: #3a3a3a;
        }

        .error-msg {
            color: #ff6b6b;
            text-align: center;
            margin-top: 15px;
        }
    </style>
</head>
<body>

<div class="login-box">
    <div class="login-title">Вхід до гри</div>

    <form method="post" action="${pageContext.request.contextPath}/login">
        <label for="username">Логін:</label>
        <input type="text" id="username" name="username" placeholder="Ваш логін">

        <label for="password">Пароль:</label>
        <input type="password" id="password" name="password" placeholder="Ваш пароль">

        <button type="submit" class="login-btn">Увійти</button>
    </form>

    <c:if test="${not empty error}">
        <div class="error-msg">${error}</div>
    </c:if>
</div>

</body>
</html>
