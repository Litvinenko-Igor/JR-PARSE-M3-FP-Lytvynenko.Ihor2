package quest.controller;

import quest.module.GameState;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/game")
public class GameServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("player") == null) {
            resp.sendRedirect("welcome");
            return;
        }

        GameState gameState = (GameState) session.getAttribute("gameState");
        if (gameState == null) {
            resp.sendRedirect("selectGame");
            return;
        }

        req.setAttribute("currentStep", gameState.getCurrentStep());
        req.setAttribute("playerHp", gameState.getPlayerHp());
        req.getRequestDispatcher("/game.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        GameState gameState = (GameState) session.getAttribute("gameState");
        if (gameState == null) {
            resp.sendRedirect("selectGame");
            return;
        }

        if (req.getParameter("restart") != null) {
            GameState newGameState = new GameState(gameState.getScenarioName());
            session.setAttribute("gameState", newGameState);
        } else {
            try {
                int optionIndex = Integer.parseInt(req.getParameter("optionIndex"));
                gameState.processAnswer(optionIndex);
            } catch (NumberFormatException e) {
            }
        }
        resp.sendRedirect("game");
    }
}
