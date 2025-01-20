<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Create Game</title>
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
        }
        h2 {
            color: #4CAF50;
            text-align: center;
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
        input, textarea {
            width: 50%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        textarea {
            resize: none;
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
            margin-top: 20px;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
        @media (max-width: 768px) {
            input, textarea {
                width: 100%;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Create a New Game</h2>
        <form action="CreateGameServlet" method="post">
            <table>
                <tr>
                    <td><label for="name">Game Name:</label></td>
                    <td><input type="text" id="name" name="name" required></td>
                </tr>
                <tr>
                    <td><label for="min_players">Minimum Players:</label></td>
                    <td><input type="number" id="min_players" name="min_players" required></td>
                </tr>
                <tr>
                    <td><label for="max_players">Maximum Players:</label></td>
                    <td><input type="number" id="max_players" name="max_players" required></td>
                </tr>
                <tr>
                    <td><label for="rules">Game Rules:</label></td>
                    <td><textarea id="rules" name="rules" rows="5" required></textarea></td>
                </tr>
            </table>
            <input type="submit" value="Create Game">
        </form>
    </div>
</body>
</html>
