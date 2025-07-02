
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>UFO quiz</title>
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
            max-width: 90%;
            width: 500px;
        }

        h3 {
            color: #2c3e50;
            margin-bottom: 2rem;
            font-size: 1.6rem;
            line-height: 1.4;
        }

        form {
            display: flex;
            flex-direction: column;
            gap: 1rem;
        }

        button {
            padding: 1rem 2rem;
            background: linear-gradient(to right, #84fab0, #8fd3f4);
            border: none;
            border-radius: 8px;
            color: white;
            font-size: 1.1rem;
            cursor: pointer;
            transition: all 0.3s ease;
            width: 100%;
            text-align: center;
        }

        button:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(132, 250, 176, 0.4);
        }

        button:active {
            transform: translateY(0);
        }

        @media (max-width: 600px) {
            .container {
                padding: 1.5rem;
                width: 90%;
            }

            button {
                padding: 0.8rem 1.5rem;
                font-size: 1rem;
            }

            h3 {
                font-size: 1.4rem;
            }
        }
    </style>
</head>
<body>
<div class="container">
    <h3>${question}</h3>
    <form method="post" action="quest">
        <c:forEach var="opt" items="${options}">
            <button type="submit" name="answer" value="${opt}">${opt}</button>
        </c:forEach>
    </form>
</div>
</body>
</html>