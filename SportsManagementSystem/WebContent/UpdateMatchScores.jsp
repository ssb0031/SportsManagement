<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Update Match Scores</title>
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
        }
        input[type="number"], input[type="text"], textarea {
            width: 50%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
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
            input[type="number"], input[type="text"], textarea, input[type="submit"] {
                width: 100%;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Update Match Scores</h2>
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

    </div>
</body>
</html>
