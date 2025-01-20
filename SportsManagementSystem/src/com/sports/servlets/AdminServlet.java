package com.sports.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import com.sports.utils.DBConnection;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setContentType("text/html");
	    PrintWriter out = response.getWriter();

	    try (Connection conn = DBConnection.getConnection()) {
	        // Fetch users
	        String userQuery = "SELECT user_id, username, email, role FROM users";
	        PreparedStatement psUsers = conn.prepareStatement(userQuery);
	        ResultSet rsUsers = psUsers.executeQuery();

	        // Display users
	        out.println("<h1>All Users</h1>");
	        out.println("<table border='1'><tr><th>User ID</th><th>Username</th><th>Email</th><th>Role</th></tr>");
	        while (rsUsers.next()) {
	            out.println("<tr><td>" + rsUsers.getInt("user_id") + "</td>");
	            out.println("<td>" + rsUsers.getString("username") + "</td>");
	            out.println("<td>" + rsUsers.getString("email") + "</td>");
	            out.println("<td>" + rsUsers.getString("role") + "</td></tr>");
	        }
	        out.println("</table>");

	        // Fetch games
	        String gameQuery = "SELECT game_id, name, min_players, max_players FROM games";
	        PreparedStatement psGames = conn.prepareStatement(gameQuery);
	        ResultSet rsGames = psGames.executeQuery();

	        // Display games
	        out.println("<h1>All Games</h1>");
	        out.println("<table border='1'><tr><th>Game ID</th><th>Name</th><th>Min Players</th><th>Max Players</th></tr>");
	        while (rsGames.next()) {
	            out.println("<tr><td>" + rsGames.getInt("game_id") + "</td>");
	            out.println("<td>" + rsGames.getString("name") + "</td>");
	            out.println("<td>" + rsGames.getInt("min_players") + "</td>");
	            out.println("<td>" + rsGames.getInt("max_players") + "</td></tr>");
	        }
	        out.println("</table>");
	    } catch (Exception e) {
	        e.printStackTrace();
	        out.println("<p>Error fetching data: " + e.getMessage() + "</p>");
	    }
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setContentType("text/html");
	    PrintWriter out = response.getWriter();

	    // Collect data from form input
	    String athleteName = request.getParameter("athleteName");
	    String email = request.getParameter("email");
	    String sport = request.getParameter("sport");
	    String teamName = request.getParameter("teamName");
	    String password = generateRandomPassword(); // Generate a random password

	    try (Connection conn = DBConnection.getConnection()) {
	        // Insert into users table
	        String userQuery = "INSERT INTO users (username, email, password_hash, role, phone) VALUES (?, ?, ?, 'athlete', '')";
	        PreparedStatement psUser = conn.prepareStatement(userQuery, PreparedStatement.RETURN_GENERATED_KEYS);
	        psUser.setString(1, athleteName);
	        psUser.setString(2, email);
	        psUser.setString(3, hashPassword(password)); // Hash the password before saving
	        psUser.executeUpdate();

	        ResultSet rs = psUser.getGeneratedKeys();
	        int userId = 0;
	        if (rs.next()) {
	            userId = rs.getInt(1);
	        }

	        // Insert into athlete_profile table
	        String profileQuery = "INSERT INTO athlete_profile (athlete_id, sport, team_name, secondary_sport) VALUES (?, ?, ?, ?)";
	        PreparedStatement psProfile = conn.prepareStatement(profileQuery);
	        psProfile.setInt(1, userId);
	        psProfile.setString(2, sport);
	        psProfile.setString(3, teamName);
	        psProfile.setString(4, ""); // Assuming no secondary sport initially
	        psProfile.executeUpdate();

	        // Insert default stats for the athlete
	        String statsQuery = "INSERT INTO stats (athlete_id, matches_played, wins, losses, draws) VALUES (?, 0, 0, 0, 0)";
	        PreparedStatement psStats = conn.prepareStatement(statsQuery);
	        psStats.setInt(1, userId); // userId obtained from the generated keys
	        psStats.executeUpdate();

	        out.println("Athlete profile created successfully.");
	    } catch (Exception e) {
	        e.printStackTrace();
	        out.println("Error creating athlete profile: " + e.getMessage());
	    }
	}


    // Helper method to generate a simple random password
    private String generateRandomPassword() {
        return "Ath" + (int) (Math.random() * 10000); // Simple random password generator
    }

    // Helper method to hash the password using SHA-256
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

}
