package com.sports.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.sports.utils.DBConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SpectatorRegistrationServlet")
public class SpectatorRegistrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String preferences = request.getParameter("preferences");

        try (Connection conn = DBConnection.getConnection()) {
            String query = "INSERT INTO users (username, email, password_hash, role) VALUES (?, ?, ?, 'spectator')";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, password);

            ps.executeUpdate();

            String spectatorQuery = "INSERT INTO spectator_registration (username, email, preferences) VALUES (?, ?, ?)";

            PreparedStatement psSpectator = conn.prepareStatement(spectatorQuery);
            psSpectator.setString(1, username);
            psSpectator.setString(2, email);
            psSpectator.setString(3, preferences);

            psSpectator.executeUpdate();

            response.getWriter().println("Spectator registered successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
