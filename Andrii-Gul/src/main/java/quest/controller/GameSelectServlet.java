package quest.controller;

import quest.module.GameState;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/selectGame")
public class GameSelectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession(false) == null || request.getSession().getAttribute("player") == null) {
            response.sendRedirect("welcome");
            return;
        }
        request.getRequestDispatcher("/selectGame.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String selectedGame = request.getParameter("game");
        HttpSession session = request.getSession();
        session.setAttribute("selectedGame", selectedGame);

        session.removeAttribute("step");
        session.removeAttribute("result");
        session.removeAttribute("knowledgeQuestions");

        if ("forest".equals(selectedGame)) {
            GameState gameState = new GameState("forest_quest");
            session.setAttribute("gameState", gameState);
            session.removeAttribute("step");
            session.removeAttribute("result");
            response.sendRedirect("game");
        } else if ("knowledge".equals(selectedGame)) {
            response.sendRedirect("knowledgeStart.jsp");
        } else {
            response.sendRedirect("selectGame");
        }
    }
}
