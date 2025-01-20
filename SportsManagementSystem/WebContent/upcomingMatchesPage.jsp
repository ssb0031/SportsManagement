<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.sports.models.Match" %>
<!DOCTYPE html>
<html>
<head>
    <title>Upcoming Matches</title>
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
        <h2>Upcoming Matches</h2>
        <table>
            <tr>
                <th>Game</th>
                <th>Scheduled Date</th>
            </tr>
            <%
                List<Match> upcomingMatches = (List<Match>) request.getAttribute("upcomingMatches");
                if (upcomingMatches != null && !upcomingMatches.isEmpty()) {
                    for (Match match : upcomingMatches) {
            %>
                        <tr>
                            <td><%= match.getGame() %></td>
                            <td><%= match.getScheduledDate() %></td>
                        </tr>
            <%
                    }
                } else {
            %>
                    <tr>
                        <td colspan="2">No upcoming matches available.</td>
                    </tr>
            <%
                }
            %>
        </table>
    </div>
</body>
</html>
