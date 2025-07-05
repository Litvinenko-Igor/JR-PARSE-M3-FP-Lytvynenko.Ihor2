package ua.com.javarush.parse.m3.root.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ua.com.javarush.parse.m3.root.repository.QuestData;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тести для класу QuestGame")
class QuestGameTest {

    private QuestGame questGame;
    private final String playerName = "Тестовий Гравець";

    @BeforeEach
    void setUp() {
        QuestData.getScenarios();
        questGame = new QuestGame(playerName);
    }

    @Test
    @DisplayName("Перевірка конструктора та початкового стану гри")
    void testConstructorAndInitialState() {
        assertEquals(playerName, questGame.getPlayerName(), "Ім'я гравця має бути встановлене коректно.");
        assertEquals(1, questGame.getCurrentStep(), "Поточний крок гри має бути 1 на старті.");
        assertFalse(questGame.isGameOver(), "Гра не має бути завершеною на старті.");
        assertFalse(questGame.isWin(), "Гра не має бути виграною на старті.");
        assertNotNull(questGame.getCurrentScenario(), "Поточний сценарій не має бути null.");
        assertEquals("Ви прокидаєтеся від різкого сигналу тривоги. Космічна станція 'Оріон' занурюється в напівтемряву. Чутно скрегіт металу. Ваш відсік заблокований, але ви бачите розбите вікно, що веде в коридор, і невеликий вентиляційний люк.",
                questGame.getCurrentScenario().getText(), "Текст початкового сценарію не відповідає очікуваному.");
    }

    @Test
    @DisplayName("Перевірка коректного виконання дійсного першого вибору")
    void testMakeChoiceValidChoice1() {
        questGame.makeChoice(1);
        assertEquals(2, questGame.getCurrentStep(), "Після вибору 1 з сценарію 1, поточний крок має бути 2.");
        assertFalse(questGame.isGameOver(), "Гра не має бути завершеною після проміжного кроку.");
        assertFalse(questGame.isWin(), "Гра не має бути виграною після проміжного кроку.");
        assertNotNull(questGame.getCurrentScenario(), "Поточний сценарій після вибору не має бути null.");
        assertEquals("Ви ризиковано пролізли через розбите вікно до житлового коридору. Чутно віддалений гул. Попереду зачинені двері до капітанського містка та двері до інженерного відсіку.",
                questGame.getCurrentScenario().getText(), "Текст сценарію 2 не відповідає очікуваному.");
    }

    @Test
    @DisplayName("Перевірка коректного виконання дійсного другого вибору")
    void testMakeChoiceValidChoice2() {
        questGame.makeChoice(2);
        assertEquals(3, questGame.getCurrentStep(), "Після вибору 2 з сценарію 1, поточний крок має бути 3.");
        assertFalse(questGame.isGameOver(), "Гра не має бути завершеною після проміжного кроку.");
        assertFalse(questGame.isWin(), "Гра не має бути виграною після проміжного кроку.");
        assertNotNull(questGame.getCurrentScenario(), "Поточний сценарій після вибору не має бути null.");
        assertEquals("Ви пролізли вентиляційною шахтою і потрапили до занедбаного складу. Повітря тут важке. Ви бачите вихід у невідомий коридор і стару коробку з інструментами.",
                questGame.getCurrentScenario().getText(), "Текст сценарію 3 не відповідає очікуваному.");
    }

    @Test
    @DisplayName("Перевірка, що недійсний ID вибору не змінює поточний сценарій")
    void testMakeChoiceInvalidChoiceId() {
        int initialScenarioId = questGame.getCurrentStep();
        questGame.makeChoice(999);
        assertEquals(initialScenarioId, questGame.getCurrentStep(), "Сценарій не має змінитися при невірному виборі.");
        assertFalse(questGame.isGameOver(), "Гра не має бути завершеною.");
        assertFalse(questGame.isWin(), "Гра не має бути виграною.");
    }

    @Test
    @DisplayName("Перевірка переходу до програшного сценарію")
    void testMakeChoiceToLossScenario() {
        questGame.makeChoice(1);
        questGame.makeChoice(1);
        questGame.makeChoice(1);

        assertEquals(7, questGame.getCurrentStep(), "Поточний крок має бути 7 (програшний сценарій).");
        assertTrue(questGame.isGameOver(), "Гра має бути завершена після програшу.");
        assertFalse(questGame.isWin(), "Гра не має бути виграна після програшу.");
        assertEquals("Ви ввели випадковий код. Сигналізація спрацювала, і ви чуєте, як до вас наближаються охоронні системи. Ви в пастці. ГРА ЗАКІНЧЕНА.",
                questGame.getCurrentScenario().getText(), "Текст програшного сценарію не відповідає очікуваному.");
    }

    @Test
    @DisplayName("Перевірка переходу до виграшного сценарію (шлях 1)")
    void testMakeChoiceToWinScenarioPath1() {
        questGame.makeChoice(1);
        questGame.makeChoice(1);
        questGame.makeChoice(2);
        questGame.makeChoice(1);

        assertEquals(11, questGame.getCurrentStep(), "Поточний крок має бути 11 (виграшний сценарій).");
        assertTrue(questGame.isGameOver(), "Гра має бути завершена після перемоги.");
        assertTrue(questGame.isWin(), "Гра має бути виграна.");
        assertEquals("Ви зайшли на капітанський місток. Основні системи відключені, але екран показує статус рятувального шаттла. Вам вдається відправити сигнал лиха! Рятувальний шаттл прибуде за лічені хвилини. ПЕРЕМОГА!",
                questGame.getCurrentScenario().getText(), "Текст переможного сценарію 11 не відповідає очікуваному.");
    }

    @Test
    @DisplayName("Перевірка переходу до виграшного сценарію (шлях 2)")
    void testMakeChoiceToWinScenarioPath2() {
        questGame.makeChoice(2);
        questGame.makeChoice(2);
        questGame.makeChoice(1);
        questGame.makeChoice(2);
        questGame.makeChoice(2);
        questGame.makeChoice(1);

        assertEquals(18, questGame.getCurrentStep(), "Поточний крок має бути 18 (виграшний сценарій).");
        assertTrue(questGame.isGameOver(), "Гра має бути завершена після перемоги.");
        assertTrue(questGame.isWin(), "Гра має бути виграна.");
        assertEquals("Ви обережно маніпулювали важелями, і люк рятувальної капсули повільно відчинився. Ви заходите в капсулу і запускаєте її, втікаючи від приреченої станції. ПЕРЕМОГА!",
                questGame.getCurrentScenario().getText(), "Текст переможного сценарію 18 не відповідає очікуваному.");
    }

}