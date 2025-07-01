package quest.controller;

import quest.module.Player;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/welcome")
public class WelcomeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("playerName");
        Player player = new Player(name);
        HttpSession session = request.getSession();
        session.setAttribute("player", player);
        response.sendRedirect("selectGame");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/welcome.jsp").forward(request, response);
    }
}
