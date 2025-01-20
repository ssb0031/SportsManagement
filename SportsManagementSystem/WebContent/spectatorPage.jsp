<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Spectator Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background: #f7f9fc;
            color: #333;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            background: white;
            border: 1px solid #ddd;
            border-radius: 8px;
            padding: 20px 30px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            width: 60%;
            max-width: 600px;
            text-align: center;
        }
        h1 {
            color: #4CAF50;
            margin-bottom: 20px;
        }
        h2 {
            color: #4CAF50;
            margin-bottom: 20px;
        }
        form {
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        table {
            width: 100%;
            border-spacing: 15px;
        }
        label {
            font-weight: bold;
            text-align: center;
        }
        input[type="submit"] {
            width: 50%;
            padding: 10px;
            font-size: 16px;
            border: none;
            background-color: #4CAF50;
            color: white;
            cursor: pointer;
            border-radius: 4px;
            transition: background-color 0.3s;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
        @media (max-width: 768px) {
            .container {
                width: 90%;
            }
            input[type="submit"] {
                width: 100%;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Welcome, Spectator</h1>
        <h2>Live Matches</h2>
        <form action="ViewLiveMatchesServlet" method="get">
            <table>
                <tr>
                    <td><label for="view_live_matches">View Live Matches:</label></td>
                    <td><input type="submit" value="View Live Matches"></td>
                </tr>
            </table>
        </form>

        <h2>Player Stats</h2>
        <form action="ViewPlayerStatsServlet" method="get">
            <table>
                <tr>
                    <td><label for="view_player_stats">View Player Stats:</label></td>
                    <td><input type="submit" value="View Player Stats"></td>
                </tr>
            </table>
        </form>

        <h2>Team Stats</h2>
        <form action="ViewTeamStatsServlet" method="get">
            <table>
                <tr>
                    <td><label for="view_team_stats">View Team Stats:</label></td>
                    <td><input type="submit" value="View Team Stats"></td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>
