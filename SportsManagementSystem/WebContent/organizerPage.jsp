<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Organizer Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f7f9fc;
            color: #333;
            display: flex;
            justify-content: center;
            align-items: flex-start;
            height: 100vh;
            margin: 0;
            padding: 20px;
        }
        .container {
            background: white;
            border-radius: 8px;
            padding: 20px 30px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 800px;
        }
        h1 {
            color: #4CAF50;
            text-align: center;
            margin-bottom: 20px;
        }
        h2 {
            color: #333;
            margin-top: 20px;
        }
        .form-section {
            margin-bottom: 30px;
        }
        form {
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        label {
            font-weight: bold;
            text-align: center;
            margin-bottom: 8px;
        }
        input[type="text"], input[type="datetime-local"], textarea {
            width: 50%;
            padding: 8px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 4px;
            margin-bottom: 20px;
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
        table {
            width: 100%;
            margin-bottom: 20px;
        }
        table td {
            padding: 8px;
        }
        @media (max-width: 768px) {
            .container {
                width: 90%;
            }
            input[type="text"], input[type="datetime-local"], textarea, input[type="submit"] {
                width: 100%;
            }
        }
    </style>
</head>
<body>

    <div class="container">
        <h1>Welcome, Organizer</h1>
        <hr>

        <!-- Manage Athletes Section -->
        <div class="form-section">
            <h2>Manage Athletes</h2>
            <form action="ViewAthletesServlet" method="get">
                <input type="submit" value="View Athletes">
            </form>
        </div>

        <!-- Update Match Scores Section -->
        <form action="UpdateMatchScoresServlet" method="post">
    <table>
        <tr>
            <td><label for="match_id">Match ID:</label></td>
            <td><input type="number" id="match_id" name="match_id" required></td>
        </tr>
        <tr>
            <td><label for="winner_id">Winner ID:</label></td>
            <td><input type="number" id="winner_id" name="winner_id" required></td>
        </tr>
        <tr>
            <td><label for="score">Score:</label></td>
            <td><input type="text" id="score" name="score" required></td>
        </tr>
        <tr>
            <td><label for="details">Details:</label></td>
            <td><textarea id="details" name="details" rows="5" cols="30" required></textarea></td>
        </tr>
    </table>
    <input type="submit" value="Update Scores">
</form>
        

        <!-- Create Match Section -->
        <div class="form-section">
            <h2>Create a Match</h2>
            <form action="CreateMatchServlet" method="post">
                <table>
                    <tr>
                        <td><label for="game_id">Game ID:</label></td>
                        <td><input type="text" name="game_id" required></td>
                    </tr>
                    <tr>
                        <td><label for="scheduled_date">Scheduled Date:</label></td>
                        <td><input type="datetime-local" name="scheduled_date" required></td>
                    </tr>
                    <tr>
                        <td><label for="location">Location:</label></td>
                        <td><input type="text" name="location" required></td>
                    </tr>
                    <tr>
                        <td><label for="participants">Participants (Comma-Separated IDs):</label></td>
                        <td><input type="text" name="participants" required></td>
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align: center;">
                            <input type="submit" value="Create Match">
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>

</body>
</html>