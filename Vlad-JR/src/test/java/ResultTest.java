
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import root.model.Result;


@ExtendWith(MockitoExtension.class)
class ResultTest {
    private Result result;

    @BeforeEach
    void setUp() {
        result = new Result();
        result.text = "You have won!";
    }

    @Test
    void testResultGetters() {
        assertEquals("You have won!", result.getText());
    }
}