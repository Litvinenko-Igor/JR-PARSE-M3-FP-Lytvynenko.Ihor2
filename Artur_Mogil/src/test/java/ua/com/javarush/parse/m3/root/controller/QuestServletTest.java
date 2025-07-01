package ua.com.javarush.parse.m3.root.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.com.javarush.parse.m3.root.model.QuestGame;
import ua.com.javarush.parse.m3.root.repository.QuestData;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Mockito.*;

@DisplayName("Тести для QuestServlet")
class QuestServletTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private RequestDispatcher requestDispatcher;

    private QuestServlet servlet;
    private QuestGame mockQuestGame;

    @BeforeEach
    void setUp() throws IOException {
        MockitoAnnotations.openMocks(this);
        servlet = new QuestServlet();
        QuestData.getScenarios();
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        when(request.getSession()).thenReturn(session);
        when(request.getContextPath()).thenReturn("/app");
        mockQuestGame = spy(new QuestGame("Тестовий Гравець"));

    }

    @Test
    @DisplayName("Початок нової гри, встановлення імені та gamesPlayed")
    void testDoPost_StartPath_NewGameCreatedAndRedirectsToGame() throws ServletException, IOException {
        when(request.getServletPath()).thenReturn("/start");
        when(request.getParameter("playerName")).thenReturn("Новий Гравець");
        when(session.getAttribute("gamesPlayed")).thenReturn(0);

        servlet.doPost(request, response);

        verify(session, times(1)).setAttribute("playerName", "Новий Гравець");
        verify(session, times(1)).setAttribute("gamesPlayed", 1);
        verify(session, times(1)).setAttribute(eq("questGame"), any(QuestGame.class));
        verify(response).sendRedirect("/app/game");
    }

    @Test
    @DisplayName("Гра відсутня або закінчена")
    void testDoPost_GamePath_NoGameOrGameOver_RedirectsToStart() throws ServletException, IOException {
        when(request.getServletPath()).thenReturn("/game");
        when(session.getAttribute("questGame")).thenReturn(null);

        servlet.doPost(request, response);

        verify(response).sendRedirect("/app/start");
        verify(mockQuestGame, never()).makeChoice(anyInt());

        reset(response);
        when(request.getServletPath()).thenReturn("/game");
        when(session.getAttribute("questGame")).thenReturn(mockQuestGame);
        when(mockQuestGame.isGameOver()).thenReturn(true);
        servlet.doPost(request, response);
        verify(response).sendRedirect("/app/start");
        verify(mockQuestGame, never()).makeChoice(anyInt());
    }

    @Test
    @DisplayName("Зроблено дійсний вибір, гра продовжується")
    void testDoPost_GamePath_ValidChoice_GameContinuesAndRedirectsToGame() throws ServletException, IOException {
        when(request.getServletPath()).thenReturn("/game");
        when(session.getAttribute("questGame")).thenReturn(mockQuestGame);
        when(mockQuestGame.isGameOver()).thenReturn(false);
        when(request.getParameter("choice")).thenReturn("1");
        servlet.doPost(request, response);
        verify(mockQuestGame, times(1)).makeChoice(1);
        verify(response).sendRedirect("/app/game");
    }

    @Test
    @DisplayName("Вибір відсутній, гра не змінюється")
    void testDoPost_GamePath_NoChoiceParam_GameNotChangedAndRedirectsToGame() throws ServletException, IOException {
        when(request.getServletPath()).thenReturn("/game");
        when(session.getAttribute("questGame")).thenReturn(mockQuestGame);
        when(mockQuestGame.isGameOver()).thenReturn(false);
        when(request.getParameter("choice")).thenReturn(null);
        servlet.doPost(request, response);
        verify(mockQuestGame, never()).makeChoice(anyInt());
        verify(response).sendRedirect("/app/game");
    }

    @Test
    @DisplayName("Перевірка перенаправлення на /game після вибору, що призвів до перемоги")
    void testDoPost_GamePath_ChoiceLeadsToWin_RedirectsToGame() throws ServletException, IOException {
        when(request.getServletPath()).thenReturn("/game");
        when(session.getAttribute("questGame")).thenReturn(mockQuestGame);
        when(mockQuestGame.isGameOver()).thenReturn(false);
        when(request.getParameter("choice")).thenReturn("1");

        doAnswer(invocation -> {
            when(mockQuestGame.isGameOver()).thenReturn(true);
            when(mockQuestGame.isWin()).thenReturn(true);
            return null;
        }).when(mockQuestGame).makeChoice(anyInt());

        servlet.doPost(request, response);

        verify(mockQuestGame, times(1)).makeChoice(1);
        verify(response).sendRedirect("/app/game");
    }
}