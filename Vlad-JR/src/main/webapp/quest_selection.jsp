<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="ua">
<head>
    <meta charset="UTF-8">
    <title>Вибір квесту</title>
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

        .quest-box {
            background-color: rgba(46, 46, 46, 0.85);
            padding: 30px 40px;
            border-radius: 12px;
            box-shadow: 0 0 10px rgba(0,0,0,0.5);
            max-width: 600px;
            width: 100%;
        }

        .quest-title {
            font-size: 1.8em;
            margin-bottom: 20px;
            text-align: center;
            text-shadow: 1px 1px 4px rgba(0,0,0,0.5);
        }

        .quest-list {
            list-style: none;
            padding: 0;
            margin: 0;
        }

        .quest-list li {
            margin-bottom: 15px;
        }

        .quest-btn {
            padding: 12px 24px;
            font-size: 1.1em;
            border: none;
            border-radius: 8px;
            background-color: #2e2e2e;
            color: #fff;
            cursor: pointer;
            width: 100%;
            transition: background-color 0.3s ease;
        }

        .quest-btn:hover {
            background-color: #3a3a3a;
        }
    </style>
</head>
<body>

<div class="quest-box">
    <div class="quest-title">Оберіть квест</div>
    <ul class="quest-list">
        <c:forEach var="quest" items="${quests}">
            <li>
                <form action="${pageContext.request.contextPath}/quests" method="post">
                    <input type="hidden" name="questPath" value="${quest.path}" />
                    <button type="submit" class="quest-btn">
                        <c:out value="${quest.name}" />
                    </button>
                </form>
            </li>
        </c:forEach>
    </ul>
</div>

</body>
</html>
