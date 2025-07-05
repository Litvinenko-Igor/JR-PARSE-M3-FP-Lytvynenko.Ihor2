package ua.com.javarush.parse.m3.root.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ua.com.javarush.parse.m3.root.model.Scenario;
import ua.com.javarush.parse.m3.root.model.Choice;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тести для сховища даних QuestData")
class QuestDataTest {

    private static Map<Integer, Scenario> scenarios;

    @BeforeAll
    static void setUpAll() {
        scenarios = QuestData.getScenarios();
    }

    @Test
    @DisplayName("Перевірка, що карта сценаріїв не є null")
    void testScenariosMapIsNotNull() {
        assertNotNull(scenarios, "Карта сценаріїв не має бути null після ініціалізації.");
    }

    @Test
    @DisplayName("Перевірка, що карта сценаріїв не порожня")
    void testScenariosMapIsNotEmpty() {
        assertFalse(scenarios.isEmpty(), "Карта сценаріїв не має бути порожньою.");
    }

    @Test
    @DisplayName("Перевірка кількості завантажених сценаріїв")
    void testNumberOfScenariosLoaded() {
        assertEquals(18, scenarios.size(), "Кількість завантажених сценаріїв не відповідає очікуваній.");
    }

    @Test
    @DisplayName("Перевірка вмісту стартового сценарію (ID 1)")
    void testStartScenarioContent() {
        Scenario scenario1 = scenarios.get(1);
        assertNotNull(scenario1, "Сценарій з ID 1 має існувати.");
        assertEquals("Ви прокидаєтеся від різкого сигналу тривоги. Космічна станція 'Оріон' занурюється в напівтемряву. Чутно скрегіт металу. Ваш відсік заблокований, але ви бачите розбите вікно, що веде в коридор, і невеликий вентиляційний люк.",
                scenario1.getText(), "Текст сценарію 1 невірний.");
        assertNotNull(scenario1.getChoice1(), "Сценарій 1 повинен мати перший вибір.");
        assertNotNull(scenario1.getChoice2(), "Сценарій 1 повинен мати другий вибір.");
        assertFalse(scenario1.isGameOver(), "Сценарій 1 не має бути закінченим.");
        assertFalse(scenario1.isWin(), "Сценарій 1 не має бути виграшним.");
    }

    @Test
    @DisplayName("Перевірка вмісту сценарію з одним вибором (ID 6)")
    void testScenarioWithOneChoiceContent() {
        Scenario scenario6 = scenarios.get(6);
        assertNotNull(scenario6, "Сценарій з ID 6 має існувати.");
        assertEquals("У коробці ви знайшли набір універсальних інструментів. Вони можуть бути корисними. Ви повертаєтеся до виходу зі складу.",
                scenario6.getText(), "Текст сценарію 6 невірний.");
        assertNotNull(scenario6.getChoice1(), "Сценарій 6 повинен мати перший вибір.");
        assertNull(scenario6.getChoice2(), "Сценарій 6 не повинен мати другого вибору (він null).");
    }

    @Test
    @DisplayName("Перевірка вмісту програшного сценарію (ID 7)")
    void testLossScenario7Content() {
        Scenario scenario7 = scenarios.get(7);
        assertNotNull(scenario7, "Сценарій з ID 7 має існувати.");
        assertEquals("Ви ввели випадковий код. Сигналізація спрацювала, і ви чуєте, як до вас наближаються охоронні системи. Ви в пастці. ГРА ЗАКІНЧЕНА.",
                scenario7.getText(), "Текст сценарію 7 (програш) невірний.");
        assertTrue(scenario7.isGameOver(), "Сценарій 7 має бути закінченим.");
        assertFalse(scenario7.isWin(), "Сценарій 7 має бути програшним.");
        assertNull(scenario7.getChoice1(), "Програшний сценарій 7 не повинен мати виборів.");
        assertNull(scenario7.getChoice2(), "Програшний сценарій 7 не повинен мати виборів.");
    }

    @Test
    @DisplayName("Перевірка вмісту переможного сценарію (ID 11)")
    void testWinScenario11Content() {
        Scenario scenario11 = scenarios.get(11);
        assertNotNull(scenario11, "Сценарій з ID 11 має існувати.");
        assertEquals("Ви зайшли на капітанський місток. Основні системи відключені, але екран показує статус рятувального шаттла. Вам вдається відправити сигнал лиха! Рятувальний шаттл прибуде за лічені хвилини. ПЕРЕМОГА!",
                scenario11.getText(), "Текст сценарію 11 (перемога) невірний.");
        assertTrue(scenario11.isGameOver(), "Сценарій 11 має бути закінченим (перемога - це також закінчення).");
        assertTrue(scenario11.isWin(), "Сценарій 11 має бути виграшним.");
        assertNull(scenario11.getChoice1(), "Переможний сценарій 11 не повинен мати виборів.");
        assertNull(scenario11.getChoice2(), "Переможний сценарій 11 не повинен мати виборів.");
    }

    @Test
    @DisplayName("Перевірка вмісту переможного сценарію (ID 18)")
    void testWinScenario18Content() {
        Scenario scenario18 = scenarios.get(18);
        assertNotNull(scenario18, "Сценарій з ID 18 має існувати.");
        assertEquals("Ви обережно маніпулювали важелями, і люк рятувальної капсули повільно відчинився. Ви заходите в капсулу і запускаєте її, втікаючи від приреченої станції. ПЕРЕМОГА!",
                scenario18.getText(), "Текст сценарію 18 (перемога) невірний.");
        assertTrue(scenario18.isGameOver(), "Сценарій 18 має бути закінченим.");
        assertTrue(scenario18.isWin(), "Сценарій 18 має бути виграшним.");
        assertNull(scenario18.getChoice1(), "Переможний сценарій 18 не повинен мати виборів.");
        assertNull(scenario18.getChoice2(), "Переможний сценарій 18 не повинен мати виборів.");
    }

    @Test
    @DisplayName("Перевірка зв'язку сценарію 1 до сценарію 2 через вибір 1")
    void testScenario1ToScenario2Connection() {
        Scenario scenario1 = scenarios.get(1);
        Choice choice1 = scenario1.getChoice1();
        assertNotNull(choice1, "Вибір 1 сценарію 1 не має бути null.");
        assertEquals(1, choice1.getId(), "ID вибору 1 сценарію 1 невірний.");
        assertEquals("Вибити розбите вікно та пробратись у коридор", choice1.getDescription(), "Опис вибору 1 сценарію 1 невірний.");
        assertEquals(2, choice1.getNextStepId(), "Вибір 1 сценарію 1 має вести до сценарію 2.");
    }

    @Test
    @DisplayName("Перевірка зв'язку сценарію 4 до сценарію 7 (програш) через вибір 1")
    void testScenario4ToScenario7LossConnection() {
        Scenario scenario4 = scenarios.get(4);
        Choice choice1 = scenario4.getChoice1();
        assertNotNull(choice1, "Вибір 1 сценарію 4 не має бути null.");
        assertEquals(7, choice1.getNextStepId(), "Вибір 1 сценарію 4 має вести до сценарію 7.");
    }

    @Test
    @DisplayName("Перевірка зв'язку сценарію 8 до сценарію 11 (перемога) через вибір 1")
    void testScenario8ToScenario11WinConnection() {
        Scenario scenario8 = scenarios.get(8);
        Choice choice1 = scenario8.getChoice1();
        assertNotNull(choice1, "Вибір 1 сценарію 8 не має бути null.");
        assertEquals(11, choice1.getNextStepId(), "Вибір 1 сценарію 8 має вести до сценарію 11.");
    }

    @Test
    @DisplayName("Перевірка, що виклики getScenarios() завжди повертають один і той самий об'єкт")
    void testGetScenariosReturnsSameInstance() {
        Map<Integer, Scenario> scenarios1 = QuestData.getScenarios();
        Map<Integer, Scenario> scenarios2 = QuestData.getScenarios();
        assertSame(scenarios1, scenarios2, "Метод getScenarios() має повертати один і той самий екземпляр карти.");
    }

    @Test
    @DisplayName("Перевірка, що сценарії можуть бути додані після ініціалізації (Map є mutable)")
    void testScenariosMapIsMutable() {
        int initialSize = scenarios.size();
        scenarios.put(999, new Scenario("Тестовий сценарій для mutable", null, null));
        assertEquals(initialSize + 1, scenarios.size(), "Розмір карти сценаріїв має збільшитись після додавання, оскільки HashMap є mutable.");
    }
}