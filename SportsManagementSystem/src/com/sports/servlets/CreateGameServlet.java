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

@WebServlet("/CreateGameServlet")
public class CreateGameServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        int minPlayers = Integer.parseInt(request.getParameter("min_players"));
        int maxPlayers = Integer.parseInt(request.getParameter("max_players"));
        String rules = request.getParameter("rules");

        try (Connection conn = DBConnection.getConnection()) {
            String query = "INSERT INTO games (name, min_players, max_players, rules) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setInt(2, minPlayers);
            ps.setInt(3, maxPlayers);
            ps.setString(4, rules);

            ps.executeUpdate();

            PrintWriter out = response.getWriter();
            out.println("Game created successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
