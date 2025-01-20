package com.sports.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.sports.utils.DBConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UpdateMatchScoresServlet")
public class UpdateMatchScoresServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Initialize variables
        int matchId = -1;
        int winnerId = -1;
        String score = request.getParameter("score");
        String details = request.getParameter("details");

        try {
            // Validate match_id and winner_id
            String matchIdParam = request.getParameter("match_id");
            String winnerIdParam = request.getParameter("winner_id");

            // Debug: Log the parameters to check if they're coming through
            System.out.println("Debug: match_id = " + matchIdParam);
            System.out.println("Debug: winner_id = " + winnerIdParam);

            // Ensure parameters are valid and non-empty
            if (matchIdParam == null || matchIdParam.trim().isEmpty()) {
                throw new IllegalArgumentException("Match ID is required and must not be empty.");
            }
            if (winnerIdParam == null || winnerIdParam.trim().isEmpty()) {
                throw new IllegalArgumentException("Winner ID is required and must not be empty.");
            }

            matchId = Integer.parseInt(matchIdParam);
            winnerId = Integer.parseInt(winnerIdParam);

            // Ensure score and details are also valid
            if (score == null || score.trim().isEmpty()) {
                throw new IllegalArgumentException("Score is required.");
            }
            if (details == null || details.trim().isEmpty()) {
                throw new IllegalArgumentException("Details are required.");
            }

            // Proceed with database operation
            try (Connection conn = DBConnection.getConnection()) {
                // Insert match result
                String query = "INSERT INTO match_results (match_id, winner_id, score, details) VALUES (?, ?, ?, ?)";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setInt(1, matchId);
                ps.setInt(2, winnerId);
                ps.setString(3, score);
                ps.setString(4, details);

                ps.executeUpdate();

                // Update stats for the winner
                String updateStatsQuery = "UPDATE stats SET matches_played = matches_played + 1, wins = wins + 1 WHERE athlete_id = ?";
                PreparedStatement psUpdateStats = conn.prepareStatement(updateStatsQuery);
                psUpdateStats.setInt(1, winnerId); // Assuming winnerId is passed in the request
                psUpdateStats.executeUpdate();

                // Send a success message
                out.println("<p>Match result updated successfully and winner's stats updated!</p>");
            } catch (Exception e) {
                e.printStackTrace();
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                out.println("<p>An error occurred while updating the match result.</p>");
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println("<p>Invalid input: match_id and winner_id should be integers.</p>");
        } catch (IllegalArgumentException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println("<p>" + e.getMessage() + "</p>");
        }
    }
}
