<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.sports.models.LeaderboardEntry" %>

<!DOCTYPE html>
<html>
<head>
    <title>Leaderboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 80%;
            margin: 20px auto;
            background: #fff;
            padding: 20px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
        }
        h2 {
            text-align: center;
            color: #333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #ccc;
            padding: 10px;
            text-align: center;
        }
        th {
            background-color: #007BFF;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Leaderboard</h2>
        <table>
            <tr>
                <th>Athlete</th>
                <th>Wins</th>
                <th>Losses</th>
                <th>Draws</th>
            </tr>
            <%
                List<LeaderboardEntry> leaderboard = (List<LeaderboardEntry>) request.getAttribute("leaderboard");
                if (leaderboard != null && !leaderboard.isEmpty()) {
                    for (LeaderboardEntry entry : leaderboard) {
            %>
                <tr>
                    <td><%= entry.getUsername() %></td>
                    <td><%= entry.getWins() %></td>
                    <td><%= entry.getLosses() %></td>
                    <td><%= entry.getDraws() %></td>
                </tr>
            <%
                    }
                } else {
            %>
                <tr>
                    <td colspan="4">No data available</td>
                </tr>
            <%
                }
            %>
        </table>
    </div>
</body>
</html>
