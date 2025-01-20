package com.sports.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.sports.utils.DBConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ScheduleMatchServlet")
public class ScheduleMatchServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Retrieve inputs
        String gameIdStr = request.getParameter("game_id");
        String organizerIdStr = request.getParameter("organizer_id");
        String rawScheduledDate = request.getParameter("scheduled_date");
        String location = request.getParameter("location");
        String participants = request.getParameter("participants");

        try {
            // Validate input
            if (gameIdStr == null || organizerIdStr == null || rawScheduledDate == null || location == null || participants == null) {
                throw new IllegalArgumentException("All fields are required.");
            }

            int gameId = Integer.parseInt(gameIdStr);
            int organizerId = Integer.parseInt(organizerIdStr);

            // Parse and format the scheduled date
            DateTimeFormatter isoFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            LocalDateTime scheduledDateTime = LocalDateTime.parse(rawScheduledDate, isoFormatter);
            DateTimeFormatter dbFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String scheduledDate = scheduledDateTime.format(dbFormatter);

            try (Connection conn = DBConnection.getConnection()) {
                conn.setAutoCommit(false); // Start transaction

                // Step 1: Validate Game ID
                String validateGameQuery = "SELECT COUNT(*) FROM games WHERE game_id = ?";
                try (PreparedStatement psValidateGame = conn.prepareStatement(validateGameQuery)) {
                    psValidateGame.setInt(1, gameId);
                    try (ResultSet rs = psValidateGame.executeQuery()) {
                        if (rs.next() && rs.getInt(1) == 0) {
                            throw new IllegalArgumentException("Invalid Game ID.");
                        }
                    }
                }

                // Step 2: Validate Organizer ID
                String validateOrganizerQuery = "SELECT COUNT(*) FROM users WHERE user_id = ?";
                try (PreparedStatement psValidateOrganizer = conn.prepareStatement(validateOrganizerQuery)) {
                    psValidateOrganizer.setInt(1, organizerId);
                    try (ResultSet rs = psValidateOrganizer.executeQuery()) {
                        if (rs.next() && rs.getInt(1) == 0) {
                            throw new IllegalArgumentException("Invalid Organizer ID.");
                        }
                    }
                }

                // Step 3: Insert into `matches` table
                String insertMatchQuery = "INSERT INTO matches (game_id, organizer_id, scheduled_date, location) VALUES (?, ?, CAST(? AS TIMESTAMP), ?)";
                int matchId;
                try (PreparedStatement psInsertMatch = conn.prepareStatement(insertMatchQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
                    psInsertMatch.setInt(1, gameId);
                    psInsertMatch.setInt(2, organizerId);
                    psInsertMatch.setString(3, scheduledDate);
                    psInsertMatch.setString(4, location);

                    psInsertMatch.executeUpdate();
                    try (ResultSet generatedKeys = psInsertMatch.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            matchId = generatedKeys.getInt(1);
                        } else {
                            throw new IllegalArgumentException("Failed to retrieve match ID.");
                        }
                    }
                }

                // Step 4: Insert participants into `participants` table
                String insertParticipantQuery = "INSERT INTO participants (match_id, athlete_id) VALUES (?, ?)";
                try (PreparedStatement psInsertParticipants = conn.prepareStatement(insertParticipantQuery)) {
                    String[] participantIds = participants.split(",");
                    for (String participantIdStr : participantIds) {
                        int participantId = Integer.parseInt(participantIdStr.trim());
                        psInsertParticipants.setInt(1, matchId);
                        psInsertParticipants.setInt(2, participantId);
                        psInsertParticipants.addBatch();
                    }
                    psInsertParticipants.executeBatch();
                }

                conn.commit(); // Commit the transaction
                out.println("<p>Match scheduled successfully!</p>");
            }
        } catch (IllegalArgumentException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println("<p>Error: " + e.getMessage() + "</p>");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.println("<p>Error scheduling match: " + e.getMessage() + "</p>");
        }
    }
}
