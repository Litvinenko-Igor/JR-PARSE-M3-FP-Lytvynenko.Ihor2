package ua.com.javarush.parse.m3.root.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ua.com.javarush.parse.m3.root.controller.QuestServlet;
import ua.com.javarush.parse.m3.root.model.Quest;
import ua.com.javarush.parse.m3.root.model.Quest.Decision;
import ua.com.javarush.parse.m3.root.model.Quest.Option;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

class QuestServletTest {

    private QuestServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private RequestDispatcher dispatcher;

    @BeforeEach
    void setUp() {
        servlet = new QuestServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        dispatcher = mock(RequestDispatcher.class);

        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);

        Quest.Option option = new Option("Test", "Story", "next");
        Quest.Decision decision = new Decision("Prompt", List.of(option));
        Quest quest = new Quest("Test Title", "Intro", Map.of("start", decision, "next", decision));
        servlet.setQuest(quest);
    }

    @Test
    void testDoGet_DefaultFlow() throws Exception {
        when(request.getParameter("next")).thenReturn("start");
        when(session.getAttribute("currentStep")).thenReturn(null);

        servlet.doGet(request, response);

        verify(dispatcher).forward(request, response);
        verify(request).setAttribute(eq("title"), eq("Test Title"));
        verify(request).setAttribute(eq("story"), eq("Intro"));
    }

    @Test
    void testDoGet_WithChoice() throws Exception {
        when(request.getParameter("next")).thenReturn("start");
        when(request.getParameter("choiceIndex")).thenReturn("0");
        when(session.getAttribute("currentStep")).thenReturn("start");

        servlet.doGet(request, response);

        verify(dispatcher).forward(request, response);
    }

    @Test
    void testDoGet_DeadOutcome() throws Exception {
        when(request.getParameter("next")).thenReturn("DEAD");
        when(session.getAttribute("attempts")).thenReturn(1);

        Quest.Option option = new Quest.Option("Test", "Story", "DEAD");
        Quest.Decision decision = new Quest.Decision("Prompt", List.of(option));
        Quest testQuest = new Quest("Title", "Intro", Map.of("DEAD", decision));
        servlet.setQuest(testQuest);

        servlet.doGet(request, response);

        verify(session).setAttribute("attempts", 2);
        verify(request).setAttribute("outcome", "dead");
        verify(dispatcher).forward(request, response);
    }




    @Test
    void testDoGet_WinOutcome() throws Exception {
        when(request.getParameter("next")).thenReturn("WIN");
        when(session.getAttribute("attempts")).thenReturn(1);

        Quest.Option option = new Quest.Option("Test", "Story", "WIN");
        Quest.Decision decision = new Quest.Decision("Prompt", List.of(option));
        Quest testQuest = new Quest("Title", "Intro", Map.of("WIN", decision));
        servlet.setQuest(testQuest);

        servlet.doGet(request, response);

        verify(session).setAttribute("attempts", 2);
        verify(request).setAttribute("outcome", "win");
        verify(dispatcher).forward(request, response);
    }

    @Test
    void testDoGet_InvalidChoiceIndex() throws Exception {
        when(request.getParameter("choiceIndex")).thenReturn("abc");
        when(session.getAttribute("currentStep")).thenReturn("start");
        when(request.getParameter("next")).thenReturn("start");

        servlet.doGet(request, response);

        verify(dispatcher).forward(request, response);
    }

}
