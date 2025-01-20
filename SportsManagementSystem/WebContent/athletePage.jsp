<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Athlete Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background: #f7f9fc;
            color: #333;
            text-align: center;
        }
        h1 {
            background-color: #4CAF50;
            color: white;
            padding: 20px 0;
            margin: 0;
        }
        h2 {
            color: #4CAF50;
            margin-bottom: 20px;
        }
        .container {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            min-height: 100vh;
        }
        .form-container {
            background: white;
            border: 1px solid #ddd;
            border-radius: 8px;
            padding: 20px;
            margin-top: 20px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            width: 60%;
        }
        .form-container table {
            margin: 0 auto;
            width: 100%;
            border-spacing: 10px;
        }
        .form-container table td {
            text-align: center;
            padding: 10px;
        }
        label {
            font-weight: bold;
        }
        input {
            width: 50%;
            padding: 8px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        input[type="submit"] {
            width: auto;
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
            font-size: 16px;
            padding: 10px 20px;
            transition: background-color 0.3s;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
        @media (max-width: 768px) {
            input {
                width: 100%;
            }
        }
    </style>
</head>
<body>
    <h1>Welcome, Athlete</h1>
    <div class="container">
        <div class="form-container">
            <h2>Your Stats</h2>
            <form action="ViewStatsServlet" method="get">
                <table>
                    <tr>
                        <td><label>View Stats</label></td>
                        <td><input type="submit" value="View Stats"></td>
                    </tr>
                </table>
            </form>
        </div>

        <div class="form-container">
            <h2>Upcoming Matches</h2>
            <form action="ViewUpcomingMatchesServlet" method="get">
                <table>
                    <tr>
                        <td><label>View Matches</label></td>
                        <td><input type="submit" value="View Upcoming Matches"></td>
                    </tr>
                </table>
            </form>
        </div>

        <div class="form-container">
            <h2>Leaderboards</h2>
            <form action="ViewLeaderboardsServlet" method="get">
                <table>
                    <tr>
                        <td><label>View Leaderboards</label></td>
                        <td><input type="submit" value="View Leaderboards"></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</body>
</html>
