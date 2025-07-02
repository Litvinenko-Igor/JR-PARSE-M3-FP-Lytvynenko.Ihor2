package root.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.*;
import java.net.URL;
import java.net.URISyntaxException;
import java.nio.file.*;

@WebServlet(name = "QuestSelectionServlet", value = "/quests")
public class QuestSelectionServlet extends HttpServlet {

    // GET request shows available quests
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Redirect to login if user is not authenticated
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Load available quests from the filesystem
        List<QuestItem> questList = loadQuests();

        // Pass quest list to JSP
        request.setAttribute("quests", questList);

        // Forward to quest selection page
        request.getRequestDispatcher("/quest_selection.jsp").forward(request, response);
    }

    // Reads quest folders and loads quest names + paths from each quest.json file
    private List<QuestItem> loadQuests() throws IOException {
        List<QuestItem> quests = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        try {
            // Get the base folder "QuestsRepository" from resources
            URL url = getClass().getClassLoader().getResource("QuestsRepository");
            if (url == null) throw new IOException("QuestsRepository not found");

            // Convert to path so we can use Java NIO
            Path questsPath = Paths.get(url.toURI());

            // Iterate over all folders in "QuestsRepository"
            try (DirectoryStream<Path> directories = Files.newDirectoryStream(questsPath)) {
                for (Path dir : directories) {
                    if (Files.isDirectory(dir)) {
                        // Look for quest.json inside each folder
                        Path questFile = dir.resolve("quest.json");
                        if (Files.exists(questFile)) {
                            try (InputStream is = Files.newInputStream(questFile)) {
                                // Parse the JSON
                                JsonNode root = mapper.readTree(is);

                                // Get quest name from JSON, or fallback to folder name
                                String name = root.has("quest_name")
                                        ? root.get("quest_name").asText()
                                        : dir.getFileName().toString();

                                // Build relative path for later use
                                String relativePath = "QuestsRepository/" + dir.getFileName() + "/quest.json";

                                quests.add(new QuestItem(name, relativePath));
                            }
                        }
                    }
                }
            }
        } catch (URISyntaxException e) {
            // This may happen if the path cannot be resolved from URL
            throw new IOException("Invalid URI syntax for quests folder", e);
        }

        return quests;
    }

    public static class QuestItem {
        private final String name;
        private final String path;

        public QuestItem(String name, String path) {
            this.name = name;
            this.path = path;
        }

        public String getName() {
            return name;
        }

        public String getPath() {
            return path;
        }
    }

    // POST request triggered when user selects a quest
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        // Get selected quest path from form
        String questPath = request.getParameter("questPath");

        // If empty or null, redirect back
        if (questPath == null || questPath.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/quests");
            return;
        }

        // Save quest path in session
        request.getSession().setAttribute("currentQuestPath", questPath);

        // Redirect to quest introduction page
        response.sendRedirect(request.getContextPath() + "/quest_main");
    }
}
