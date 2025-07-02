package root.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "WelcomeServlet", value = "/")
public class WelcomeServlet extends HttpServlet {

    // Handles GET requests to "/" (main entry point)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Check if a user is logged in (optional display info)
        Object userObj = request.getSession().getAttribute("user");

        if (userObj != null) {
            // Retrieve user_wins for potential display (not used here, but maybe for JSP)
            request.getSession().getAttribute("user_wins");
        }

        // Always show welcome page regardless of login status
        request.getRequestDispatcher("/welcome_page.jsp").forward(request, response);
    }

    // Handles POST requests from the welcome page (e.g. "Continue" button)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        // If the user is not logged in, redirect to login page
        Object user = request.getSession().getAttribute("user");

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            // If already logged in, go directly to quest selection
            response.sendRedirect(request.getContextPath() + "/quests");
        }
    }
}
