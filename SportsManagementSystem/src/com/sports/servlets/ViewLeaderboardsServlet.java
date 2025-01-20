package com.sports.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sports.models.LeaderboardEntry;
import com.sports.utils.DBConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ViewLeaderboardsServlet")
public class ViewLeaderboardsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<LeaderboardEntry> leaderboard = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection()) {
        	String query = "SELECT s.athlete_id, u.username, s.wins, s.losses, s.draws " +
                    "FROM stats s " +
                    "JOIN users u ON s.athlete_id = u.user_id " +
                    "ORDER BY s.wins DESC";

            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                LeaderboardEntry entry = new LeaderboardEntry(
                    rs.getString("username"),
                    rs.getInt("wins"),
                    rs.getInt("losses"),
                    rs.getInt("draws")
                );
                leaderboard.add(entry);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Pass leaderboard data to the JSP
        request.setAttribute("leaderboard", leaderboard);
        request.getRequestDispatcher("leaderboardPage.jsp").forward(request, response);
    }
    

}
