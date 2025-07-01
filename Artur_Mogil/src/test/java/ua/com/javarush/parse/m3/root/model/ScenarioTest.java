package ua.com.javarush.parse.m3.root.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тести для класу Scenario")
class ScenarioTest {

    private Scenario scenario;
    private Choice choice1;
    private Choice choice2;

    @BeforeEach
    void setUp() {
        choice1 = new Choice(1, "Вибір 1", 2);
        choice2 = new Choice(2, "Вибір 2", 3);
        scenario = new Scenario("Це опис сценарію.", choice1, choice2);
    }

    @Test
    @DisplayName("Перевірка отримання тексту сценарію")
    void testGetText() {
        assertEquals("Це опис сценарію.", scenario.getText());
    }

    @Test
    @DisplayName("Перевірка отримання першого вибору сценарію")
    void testGetChoice1() {
        assertNotNull(scenario.getChoice1());
        assertEquals(choice1, scenario.getChoice1());
    }

    @Test
    @DisplayName("Перевірка отримання другого вибору сценарію")
    void testGetChoice2() {
        assertNotNull(scenario.getChoice2());
        assertEquals(choice2, scenario.getChoice2());
    }


    @Test
    @DisplayName("Перевірка властивості 'перемога' для сценарію")
    void testIsWin() {
        assertFalse(scenario.isWin(), "Невиграшний сценарій має бути false");
        Scenario winScenario = new Scenario("Ви перемогли!", null, null, true, true);
        assertTrue(winScenario.isWin(), "Виграшний сценарій має бути true");
    }


}