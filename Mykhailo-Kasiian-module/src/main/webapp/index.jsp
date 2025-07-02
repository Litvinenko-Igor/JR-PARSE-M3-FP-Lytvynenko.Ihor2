
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Log in</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background: linear-gradient(120deg, #84fab0 0%, #8fd3f4 100%);
            height: 100vh;
            margin: 0;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }

        .container {
            background: rgba(255, 255, 255, 0.9);
            padding: 2rem 3rem;
            border-radius: 15px;
            box-shadow: 0 8px 24px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        h2 {
            color: #2c3e50;
            margin-bottom: 1.5rem;
            font-size: 1.8rem;
        }

        form {
            display: flex;
            flex-direction: column;
            gap: 1rem;
        }

        input[type="text"] {
            padding: 0.8rem;
            border: 2px solid #e0e0e0;
            border-radius: 8px;
            font-size: 1rem;
            width: 250px;
            transition: border-color 0.3s ease;
        }

        input[type="text"]:focus {
            outline: none;
            border-color: #84fab0;
        }

        input[type="submit"] {
            padding: 0.8rem 1.5rem;
            background: linear-gradient(to right, #84fab0, #8fd3f4);
            border: none;
            border-radius: 8px;
            color: white;
            font-size: 1rem;
            cursor: pointer;
            transition: transform 0.2s ease;
        }

        input[type="submit"]:hover {
            transform: translateY(-2px);
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Введіть ім'я гравця:</h2>
    <form method="post" action="quest">
        <input type="text" name="playerName" required placeholder="Ваше ім'я"/>
        <input type="submit" value="Почати гру"/>
    </form>
</div>
</body>
</html>