<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Schedule Match</title>
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
            display: block;
            margin-bottom: 8px;
        }
        input[type="number"], input[type="datetime-local"] {
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
        @media (max-width: 768px) {
            .container {
                width: 90%;
            }
            input[type="number"], input[type="datetime-local"], input[type="submit"] {
                width: 100%;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Schedule a New Match</h2>
        <form action="ScheduleMatchServlet" method="post">
            <table>
                <tr>
                    <td><label for="game_id">Game ID:</label></td>
                    <td><input type="number" id="game_id" name="game_id" required></td>
                </tr>
                <tr>
                    <td><label for="organizer_id">Organizer ID:</label></td>
                    <td><input type="number" id="organizer_id" name="organizer_id" required></td>
                </tr>
                <tr>
                    <td><label for="scheduled_date">Scheduled Date:</label></td>
                    <td><input type="datetime-local" id="scheduled_date" name="scheduled_date" required></td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: center;">
                        <input type="submit" value="Schedule Match">
                    </td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>
