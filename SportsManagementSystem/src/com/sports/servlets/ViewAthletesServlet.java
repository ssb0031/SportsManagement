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

@WebServlet("/ViewAthletesServlet")
public class ViewAthletesServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT * FROM athlete_profile";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            PrintWriter out = response.getWriter();
            out.println("<table border='1'><tr><th>Athlete ID</th><th>Sport</th><th>Team Name</th><th>Secondary Sport</th></tr>");
            while (rs.next()) {
                out.println("<tr><td>" + rs.getInt("athlete_id") + "</td><td>" + rs.getString("sport") + "</td><td>" 
                            + rs.getString("team_name") + "</td><td>" + rs.getString("secondary_sport") + "</td></tr>");
            }
            out.println("</table>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
