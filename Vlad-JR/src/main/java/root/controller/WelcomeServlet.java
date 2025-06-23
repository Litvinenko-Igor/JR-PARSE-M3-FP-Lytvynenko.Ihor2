package root.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.*;


@WebServlet(name = "WelcomeServlet", value = "")
public class WelcomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("story.txt");

        if (inputStream != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder storyBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                storyBuilder.append(line).append("<br/>");
            }
            reader.close();
            request.setAttribute("story", storyBuilder.toString());
        } else {
            request.setAttribute("story", "Історія не знайдена...");
        }

        request.getRequestDispatcher("/welcome.jsp").forward(request, response);
    }
}