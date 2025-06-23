<!DOCTYPE html>
<html lang="ua">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<head>
  <title>Quest Start</title>
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

    h1 {
      font-size: 3em;
      margin-bottom: 20px;
      text-shadow: 1px 1px 4px rgba(0,0,0,0.5);
    }

    .story-box {
      background-color: rgba(46,46,46,0.85);
      padding: 20px 30px;
      border-radius: 12px;
      box-shadow: 0 0 10px rgba(0,0,0,0.5);
      max-width: 600px;
      margin-top: 30px;
      text-align: center;
    }

    .story-box p {
      font-size: 1.1em;
      line-height: 1.5;
      text-shadow: 1px 1px 3px rgba(0,0,0,0.5);
      margin: 0;
    }

    button {
      padding: 12px 24px;
      font-size: 1.2em;
      border: none;
      border-radius: 8px;
      background-color: #2e2e2e;
      color: #fff;
      cursor: pointer;
      transition: background-color 0.3s ease;
      margin-top: 20px;
    }

    button:hover {
      background-color: #3a3a3a;
    }

    form {
      display: inline;
    }
  </style>
</head>
<body>
  <h1>Привіт, програміст!</h1>
  <form action="game" method="get">
    <button type="submit">Почати квест</button>
  </form>
  <div class="story-box">
    <%= request.getAttribute("story") %>
  </div>
</body>
</html>
