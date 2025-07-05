package ua.com.javarush.parse.m3.root.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тести для класу Choice")
class ChoiceTest {

    private Choice choice;

    @BeforeEach
    void setUp() {
        choice = new Choice(1, "Вибрати опцію А", 2);
    }

    @Test
    @DisplayName("Перевірка отримання ID вибору")
    void testGetId() {
        assertEquals(1, choice.getId());
    }

    @Test
    @DisplayName("Перевірка отримання опису вибору")
    void testGetDescription() {
        assertEquals("Вибрати опцію А", choice.getDescription());
    }

}
