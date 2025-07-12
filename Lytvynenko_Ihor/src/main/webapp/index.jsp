<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Проєкт "НЛО"</title>
    <link rel="stylesheet" href="static/siteMain.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Orbitron:wght@400;700&family=Roboto:wght@300;400&display=swap" rel="stylesheet">
</head>
<body>
<div class="background-container">
    <div class="stars-container" id="stars-container"></div>
    <div class="ufo">
        <div class="ufo-dome"></div>
        <div class="ufo-body"></div>
    </div>
</div>

<div class="container">
    <h1>Ласкаво просимо</h1>
    <p>
        Ти розплющуєш очі, і перше, що відчуваєш — холод металевої підлоги під щокою. Голову стискає пульсуючий біль, а у вухах дзвенить глибока тиша. Хто ти? Де ти? Останні спогади — лише розмиті, хаотичні спалахи, наче уривки чужого сну. Спроба згадати власне ім'я викликає лише глухий біль у скронях. Серед цього хаосу в пам'яті виринає один образ: чіткий і жахливий силует, неземне сяйво, що ллється з неба. Ти згадуєш дивний, низький гул, що вібрував у самих кістках, і відчуття чогось велетенського й мовчазного, що зависло прямо над тобою. А потім... порожнеча. Твоя увага прикута до єдиного джерела світла в кімнаті — невеликого екрана, що мерехтить на стіні. На ньому повільно з'являються слова. Це не питання. Це констатація факту: «Ми знаємо, що ти все забув. Але твій час знову настав. Ми чекаємо на відповідь». Ти розумієш, що вибір, який ти зробиш зараз, визначить не тільки те, ким ти станеш, але й, можливо, долю всього, що ти знав колись.
    </p>
    <form action="game" method="get">
        <p class="form-title">Зроби свій вибір.</p>
        <label for="firstName">First name:</label>
        <input type="text" id="firstName" name="firstName">
        <br>
        <button type="submit">Почати</button>
    </form>
</div>

<script>
    document.addEventListener('DOMContentLoaded', function() {
        const starsContainer = document.getElementById('stars-container');
        if (!starsContainer) return;
        const numberOfStars = 250;

        for (let i = 0; i < numberOfStars; i++) {
            const star = document.createElement('div');
            star.classList.add('star');
            const size = Math.random() * 2.5 + 0.5;
            star.style.width = `${size}px`;
            star.style.height = `${size}px`;
            star.style.top = `${Math.random() * 100}%`;
            star.style.left = `${Math.random() * 100}%`;
            star.style.animationDelay = `${Math.random() * 6}s`;
            star.style.animationDuration = `${Math.random() * 4 + 3}s`;
            starsContainer.appendChild(star);
        }
    });
</script>
</body>
</html>
