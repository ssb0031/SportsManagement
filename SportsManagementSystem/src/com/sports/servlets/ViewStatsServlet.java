package com.sports.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.sports.utils.DBConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ViewStatsServlet")
public class ViewStatsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Debugging information for session and attributes
        Integer athleteId = (Integer) request.getSession().getAttribute("athlete_id");
        out.println("<p>Debug: Session ID = " + request.getSession().getId() + "</p>");
        out.println("<p>Debug: Athlete ID from session = " + athleteId + "</p>");

        // Check if athlete_id is available
        if (athleteId == null) {
            // Redirect to login page if not logged in
            out.println("<p>You need to be logged in to view your stats.</p>");
            response.sendRedirect("index.jsp");  // Ensure this matches your login page URL
            return;
        }

        // Retrieve and display athlete's stats with additional debugging
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT g.name AS game, s.matches_played, s.wins, s.losses, s.draws " +
                           "FROM stats s " +
                           "JOIN games g ON s.game_id = g.game_id " +
                           "WHERE s.athlete_id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, athleteId);  // Use the athlete ID from the session

            // Debug: Show the executed query parameters
            out.println("<p>Debug: Executing query with athlete_id = " + athleteId + "</p>");

            ResultSet rs = ps.executeQuery();

            // Debug: Display retrieved results
            out.println("<h2>Your Stats</h2>");
            out.println("<table border='1'><tr><th>Game</th><th>Matches Played</th><th>Wins</th><th>Losses</th><th>Draws</th></tr>");
            boolean hasResults = false;
            while (rs.next()) {
                hasResults = true;
                String game = rs.getString("game");
                int matchesPlayed = rs.getInt("matches_played");
                int wins = rs.getInt("wins");
                int losses = rs.getInt("losses");
                int draws = rs.getInt("draws");

                // Debug: Log each row's details
                out.println("<p>Debug: Retrieved - Game: " + game + ", Matches Played: " + matchesPlayed 
                            + ", Wins: " + wins + ", Losses: " + losses + ", Draws: " + draws + "</p>");

                out.println("<tr><td>" + game + "</td><td>" 
                            + matchesPlayed + "</td><td>" 
                            + wins + "</td><td>" 
                            + losses + "</td><td>" 
                            + draws + "</td></tr>");
            }
            if (!hasResults) {
                out.println("<tr><td colspan='5'>No stats available for the current athlete.</td></tr>");
            }
            out.println("</table>");
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h3>Error loading stats. Please try again later.</h3>");
        }
    }
}
