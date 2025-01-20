<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Page</title>
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
        .manage-users {
            margin: 20px 0;
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
        input, select, textarea {
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
            input, select, textarea {
                width: 100%;
            }
        }
    </style>
</head>
<body>
    <h1>Welcome, Admin</h1>
    <div class="container">
        <div class="manage-users">
            <h2>Manage Users</h2>
            <form action="AdminServlet" method="get">
                <input type="submit" value="View All Users">
            </form>
        </div>

        <div class="form-container">
            <h2>Create New Game</h2>
            <form action="CreateGameServlet" method="post">
                <table>
                    <tr>
                        <td><label>Game Name:</label></td>
                        <td><input type="text" name="name" required></td>
                    </tr>
                    <tr>
                        <td><label>Minimum Players:</label></td>
                        <td><input type="number" name="min_players" required></td>
                    </tr>
                    <tr>
                        <td><label>Maximum Players:</label></td>
                        <td><input type="number" name="max_players" required></td>
                    </tr>
                    <tr>
                        <td><label>Rules:</label></td>
                        <td><textarea name="rules" required></textarea></td>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="submit" value="Create Game"></td>
                    </tr>
                </table>
            </form>
        </div>

        <div class="form-container">
            <h2>Schedule a Match</h2>
            <form action="ScheduleMatchServlet" method="post">
                <table>
                    <tr>
                        <td><label>Game ID:</label></td>
                        <td><input type="number" name="game_id" required></td>
                    </tr>
                    <tr>
                        <td><label>Organizer ID:</label></td>
                        <td><input type="number" name="organizer_id" required></td>
                    </tr>
                    <tr>
                        <td><label>Scheduled Date:</label></td>
                        <td><input type="datetime-local" name="scheduled_date" required></td>
                    </tr>
                    <tr>
                        <td><label>Location:</label></td>
                        <td><input type="text" name="location" required></td>
                    </tr>
                    <tr>
                        <td><label>Participants (Comma-Separated IDs):</label></td>
                        <td><input type="text" name="participants" required></td>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="submit" value="Schedule Match"></td>
                    </tr>
                </table>
            </form>
        </div>

        <div class="form-container">
            <h2>Create New Athlete Profile</h2>
            <form action="CreateAthleteServlet" method="post">
                <table>
                    <tr>
                        <td><label for="athleteName">Athlete Name:</label></td>
                        <td><input type="text" id="athleteName" name="athleteName" required></td>
                    </tr>
                    <tr>
                        <td><label for="email">Email:</label></td>
                        <td><input type="email" id="email" name="email" required></td>
                    </tr>
                    <tr>
                        <td><label for="sport">Sport:</label></td>
                        <td><input type="text" id="sport" name="sport" required></td>
                    </tr>
                    <tr>
                        <td><label for="teamName">Team Name:</label></td>
                        <td><input type="text" id="teamName" name="teamName"></td>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="submit" value="Create Profile"></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</body>
</html>
