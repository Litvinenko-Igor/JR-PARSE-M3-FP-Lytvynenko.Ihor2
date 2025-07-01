<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Початок вікторини "Знавець світу"</title>
    <link rel="stylesheet" href="css/style.css">
    <style>
        body {
            transition: background-image 0.5s ease-in-out;
        }

        .continent-header {
            text-align: center;
            color: white;
            padding: 20px 0;
        }
        .continent-header h1 {
            font-size: 4em;
            text-shadow: 3px 3px 6px rgba(0,0,0,0.7);
            margin: 0;
            transition: opacity 0.5s;
            opacity: 0;
        }
        .continent-header.visible h1 {
            opacity: 1;
        }

        .game-select-container {
            margin-top: 2vh;
        }

        .bg-africa { background-image: url('/img/continents/africa.jpg'); }
        .bg-asia { background-image: url('/img/continents/asia.jpg'); }
        .bg-europe { background-image: url('/img/continents/europe.jpg'); }
        .bg-northamerica { background-image: url('/img/continents/north_america.jpg'); }
        .bg-southamerica { background-image: url('/img/continents/south_america.jpg'); }
        .bg-oceania { background-image: url('/img/continents/oceania.jpg'); }
        .bg-antarctica { background-image: url('/img/continents/antarctica.jpg'); }
        .bg-default { background-image: url('/img/3d-rendering-sestiugol-noi-tekstury-fona(1).jpg'); }

        .level-select fieldset { border: 1px solid #777; border-radius: 8px; padding: 15px; margin-top: 20px; }
        .level-select legend { padding: 0 10px; font-weight: bold; color: white; }
        .level-select label { margin-right: 20px; font-size: 18px; }
    </style>
</head>
<body class="bg-default">

<div id="continentHeader" class="continent-header">
    <h1 id="continentName"></h1>
</div>

<div class="game-select-container">
    <h2>Налаштування вікторини "Знавець світу"</h2>
    <p>Обери континент і рівень складності, щоб ми підібрали для тебе запитання.</p>

    <c:if test="${not empty error}">
        <p style="color: #ffcccc; font-weight: bold;">${error}</p>
    </c:if>

    <form method="get" action="game-knowledge">
        <label for="continentSelect">Континент:</label><br>
        <select name="continent" id="continentSelect" required style="width: 100%; padding: 10px; font-size: 16px;">
            <option value="">--Оберіть континент--</option>
            <option value="Африка" data-bg="africa">Африка</option>
            <option value="Азія" data-bg="asia">Азія</option>
            <option value="Європа" data-bg="europe">Європа</option>
            <option value="Північна Америка" data-bg="northamerica">Північна Америка</option>
            <option value="Південна Америка" data-bg="southamerica">Південна Америка</option>
            <option value="Океанія" data-bg="oceania">Океанія</option>
            <option value="Антарктида" data-bg="antarctica">Антарктида</option>
        </select>
        <br><br>
        <label for="country">Країна (необов'язково, уточнює запитання):</label><br>
        <input type="text" name="country" id="country" placeholder="Наприклад, Франція" style="width: 95%; padding: 10px; font-size: 16px;"/>
        <br><br>

        <div class="level-select">
            <fieldset>
                <legend>Рівень складності</legend>
                <label><input type="radio" name="level" value="easy" checked> Легкий (5 запитань)</label>
                <label><input type="radio" name="level" value="medium"> Середній (10 запитань)</label>
                <label><input type="radio" name="level" value="hard"> Складний (15 запитань)</label>
            </fieldset>
        </div>
        <br>
        <button type="submit" class="game-button" style="width: 100%; padding: 15px;">Почати гру</button>
    </form>
    <br>
    <a href="selectGame" class="logout-link">Повернутися до вибору гри</a>
</div>

<script>
    document.addEventListener('DOMContentLoaded', function() {
        const selectElement = document.getElementById('continentSelect');
        const bodyElement = document.body;
        const continentHeader = document.getElementById('continentHeader');
        const continentName = document.getElementById('continentName');

        const bgClasses = [
            'bg-africa', 'bg-asia', 'bg-europe', 'bg-northamerica',
            'bg-southamerica', 'bg-oceania', 'bg-antarctica', 'bg-default'
        ];

        selectElement.addEventListener('change', function() {
            bodyElement.classList.remove(...bgClasses);

            const selectedOption = this.options[this.selectedIndex];

            if (selectedOption.value) {
                const bgClass = 'bg-' + selectedOption.dataset.bg;
                bodyElement.classList.add(bgClass);

                continentName.textContent = selectedOption.textContent;
                continentHeader.classList.add('visible');
            } else {
                bodyElement.classList.add('bg-default');
                continentName.textContent = '';
                continentHeader.classList.remove('visible');
            }
        });
    });
</script>
</body>
</html>
