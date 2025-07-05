<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Проклятий будинок</title>
  <style>
    body { font-family: Arial, sans-serif; background-color: #1a1a1a; color: #fff; text-align: center; padding: 50px; }
    .container { max-width: 600px; margin: auto; }
    input, button { padding: 10px; margin: 10px; }
  </style>
</head>
<body>
<div class="container">
  <h1>Ласкаво просимо до Проклятого будинку</h1>
  <p>Ти опинився перед старовинним будинком, оточеним густим туманом. Легенди шепочуть про зниклих тут душі, а вітер приносить холодний страх. Чи наважишся ввійти?</p>
  <form action="game" method="post">
    <label for="playerName">Введи своє ім'я, сміливцю:</label>
    <input type="text" id="playerName" name="playerName" required>
    <button type="submit">Почати жах</button>
  </form>
</div>
</body>
</html>