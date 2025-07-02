import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.com.javarush.m3.fp.controller.QuizServlet;
import ua.com.javarush.m3.fp.model.Question;
import ua.com.javarush.m3.fp.repository.QuestionRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

class QuizServletTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private RequestDispatcher dispatcher;
    @Mock
    private QuestionRepository repository;

    private QuizServlet servlet;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        MockitoAnnotations.openMocks(this);
        servlet = new QuizServlet();
        // Встановлюємо repository через рефлексію в поле
        Field repositoryField = QuizServlet.class.getDeclaredField("repository");
        repositoryField.setAccessible(true);
        repositoryField.set(servlet, repository);
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("/game.jsp")).thenReturn(dispatcher);
    }

    @Test
    void testDoPostWithNewPlayerName() throws ServletException, IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        when(request.getParameter("playerName")).thenReturn("TestPlayer");
        Question startQuestion = new Question("Test description", false, null, new HashMap<>());
        when(repository.getQuestion("start")).thenReturn(startQuestion);

        Method doPostMethod = QuizServlet.class.getDeclaredMethod("doPost", HttpServletRequest.class, HttpServletResponse.class);
        doPostMethod.setAccessible(true);
        doPostMethod.invoke(servlet, request, response);

        verify(session).setAttribute("playerName", "TestPlayer");
        verify(session).setAttribute("gamesPlayed", 0);
        verify(session).setAttribute("wins", 0);
        verify(session).setAttribute("currentState", "start");
        verify(request).setAttribute(eq("gameState"), eq(startQuestion)); // Точна перевірка
        verify(dispatcher).forward(request, response);
    }

    @Test
    void testDoPostWithRestartAction() throws ServletException, IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        when(request.getParameter("action")).thenReturn("restart");
        when(session.getAttribute("gamesPlayed")).thenReturn(1);
        Question startQuestion = new Question("Test description", false, null, new HashMap<>());
        when(repository.getQuestion("start")).thenReturn(startQuestion);

        Method doPostMethod = QuizServlet.class.getDeclaredMethod("doPost", HttpServletRequest.class, HttpServletResponse.class);
        doPostMethod.setAccessible(true);
        doPostMethod.invoke(servlet, request, response);

        verify(session).setAttribute("currentState", "start");
        verify(session).setAttribute("gamesPlayed", 2);
        verify(request).setAttribute(eq("gameState"), eq(startQuestion)); // Точна перевірка
        verify(dispatcher).forward(request, response);
    }

    @Test
    void testDoPostWithChoice() throws ServletException, IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        when(request.getParameter("choice")).thenReturn("door");
        when(session.getAttribute("currentState")).thenReturn("start");
        Question hallQuestion = new Question("Hall description", false, null, new HashMap<>());
        when(repository.getQuestion("hall")).thenReturn(hallQuestion);

        Method doPostMethod = QuizServlet.class.getDeclaredMethod("doPost", HttpServletRequest.class, HttpServletResponse.class);
        doPostMethod.setAccessible(true);
        doPostMethod.invoke(servlet, request, response);

        verify(session).setAttribute("currentState", "hall");
        verify(request).setAttribute(eq("gameState"), eq(hallQuestion)); // Точна перевірка
        verify(dispatcher).forward(request, response);
    }
}