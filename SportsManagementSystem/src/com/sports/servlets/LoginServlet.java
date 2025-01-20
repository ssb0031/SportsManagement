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

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT role FROM users WHERE username = ? AND password_hash = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String role = rs.getString("role");
                switch (role) {
                    case "admin":
                        response.sendRedirect("adminPage.jsp");
                        break;
                    case "organizer":
                        response.sendRedirect("organizerPage.jsp");
                        break;
                    case "athlete":
                        response.sendRedirect("athletePage.jsp");
                        break;
                    case "spectator":
                        response.sendRedirect("spectatorPage.jsp");
                        break;
                    default:
                        response.sendRedirect("error.jsp");
                }
            } else {
                PrintWriter out = response.getWriter();
                out.println("<h3>Invalid username or password!</h3>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
