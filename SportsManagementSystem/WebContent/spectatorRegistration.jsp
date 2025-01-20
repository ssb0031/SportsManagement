<!DOCTYPE html>
<html>
<head>
    <title>Spectator Registration</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        .container {
            width: 50%;
            margin: 5% auto;
            background: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
        }
        h2 {
            text-align: center;
            color: #333;
        }
        form {
            display: flex;
            flex-direction: column;
        }
        label {
            margin-bottom: 10px;
            font-weight: bold;
        }
        input, textarea {
            margin-bottom: 15px;
            padding: 10px;
            font-size: 14px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }
        textarea {
            resize: vertical;
        }
        .submit-btn {
            background-color: #007BFF;
            color: white;
            border: none;
            cursor: pointer;
            padding: 10px;
            font-size: 16px;
            border-radius: 3px;
            transition: background-color 0.3s;
        }
        .submit-btn:hover {
            background-color: #0056b3;
        }
        .message {
            text-align: center;
            margin-top: 10px;
            font-size: 14px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Spectator Registration</h2>
        <form action="SpectatorRegistrationServlet" method="post">
            <label for="username">Username:</label>
            <input type="text" name="username" id="username" placeholder="Enter your username" required>

            <label for="email">Email:</label>
            <input type="email" name="email" id="email" placeholder="Enter your email" required>

            <label for="password">Password:</label>
            <input type="password" name="password" id="password" placeholder="Enter your password" required>

            <label for="preferences">Preferences:</label>
            <textarea name="preferences" id="preferences" placeholder="Enter your preferences for matches, teams, etc." rows="4" required></textarea>

            <button type="submit" class="submit-btn">Register</button>
        </form>

        <!-- Display a message based on servlet output -->
        <% String message = request.getParameter("message"); %>
        <% if (message != null) { %>
            <div class="message"><%= message %></div>
        <% } %>
    </div>
</body>
</html>
