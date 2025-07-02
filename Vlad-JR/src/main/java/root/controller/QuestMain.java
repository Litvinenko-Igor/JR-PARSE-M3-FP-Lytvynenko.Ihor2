package root.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


@WebServlet(name = "QuestMain", value = "/quest_main")
public class QuestMain extends HttpServlet {

    // Handles GET requests to show the quest's main story screen
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");

        // Get the path to the current quest from the session
        String questPath = (String) request.getSession().getAttribute("currentQuestPath");

        // If no quest is selected, redirect to quest list
        if (questPath == null) {
            response.sendRedirect(request.getContextPath() + "/quests");
            return;
        }

        // Build the path to the story.txt file based on quest folder
        String basePath = questPath.substring(0, questPath.lastIndexOf('/'));
        String storyFilePath = basePath + "/story.txt";

        // Try to read the story file from resources
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(storyFilePath);

        if (inputStream != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder storyBuilder = new StringBuilder();
            String line;

            // Read the story file line by line, adding <br/> for HTML display
            while ((line = reader.readLine()) != null) {
                storyBuilder.append(line).append("<br/>");
            }
            reader.close();

            // Pass story text to JSP
            request.setAttribute("story", storyBuilder.toString());
        } else {
            // If story file is not found
            request.setAttribute("story", "Історія не знайдена..."); // "Story not found..."
        }

        // Forward to JSP to render the story
        request.getRequestDispatcher("/quest_main.jsp").forward(request, response);
    }

    // Handles POST request (e.g., "Start" button), redirects to actual game
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        System.out.println("POST вызван на /quest_main"); // For debug/logging
        response.sendRedirect(request.getContextPath() + "/game");
    }
}
