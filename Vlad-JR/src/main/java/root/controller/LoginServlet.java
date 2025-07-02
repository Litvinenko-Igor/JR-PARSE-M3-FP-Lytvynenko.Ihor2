package root.controller;

import root.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    // Handles GET requests to show the login page
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    // Handles POST requests when user submits the login form
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get submitted username and password from the request
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Check if both fields are filled
        if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
            // Create a new user object with the submitted credentials
            User user = new User(username, password);

            // Store the user object and initial win count in the session
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("user_wins", 0);

            // Redirect the user to the quest selection page
            response.sendRedirect(request.getContextPath() + "/quests");
        } else {
            // If validation fails, show an error and reload login page
            request.setAttribute("error", "Введіть логін і пароль."); // "Enter username and password."
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}
