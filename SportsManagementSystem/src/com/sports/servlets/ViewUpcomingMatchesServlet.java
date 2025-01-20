package com.sports.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sports.models.Match;
import com.sports.utils.DBConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ViewUpcomingMatchesServlet")
public class ViewUpcomingMatchesServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Match> upcomingMatches = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection()) {
            // SQL query to fetch upcoming matches with game name, scheduled date, and location
            String query = "SELECT m.match_id, g.name AS game, m.scheduled_date, m.location " +
                           "FROM matches m " +
                           "JOIN games g ON m.game_id = g.game_id " +
                           "WHERE m.scheduled_date > NOW() " +
                           "ORDER BY m.scheduled_date ASC";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                // Create a new Match object with retrieved details
                Match match = new Match(
                    rs.getInt("match_id"),       // match_id
                    rs.getString("game"),        // game name
                    rs.getTimestamp("scheduled_date").toString(), // scheduled date
                    rs.getString("location")     // location
                );
                // Add the match to the list of upcoming matches
                upcomingMatches.add(match);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Pass the data to the JSP
        request.setAttribute("upcomingMatches", upcomingMatches);
        request.getRequestDispatcher("upcomingMatchesPage.jsp").forward(request, response);
    }
}
