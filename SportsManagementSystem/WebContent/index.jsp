<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Sports Management System</title>
    <style>
        body { font-family: Arial, sans-serif; }
        .container { width: 50%; margin: auto; text-align: center; }
        h1 { color: #333; }
        form { margin-top: 20px; }
        input[type="text"], input[type="password"] { width: 100%; padding: 8px; margin: 5px 0; }
        input[type="submit"] { padding: 10px 20px; background-color: #28a745; color: white; border: none; cursor: pointer; }
        input[type="submit"]:hover { background-color: #218838; }
        .message { color: red; }
    </style>
</head>
<body>
    <div class="container">
        <h1>Welcome to the Sports Management System</h1>
        
        <!-- Display error message if login fails -->
        <c:if test="${not empty errorMessage}">
            <div class="message">${errorMessage}</div>
        </c:if>

        <!-- Login Form -->
        <form action="LoginServlet" method="post">
            <h2>Login</h2>
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>

            <input type="submit" value="Login">
        </form>

        <!-- Link to Spectator Registration -->
        <p>Not an athlete or organizer? <a href="spectatorRegistration.jsp">Register as a Spectator</a></p>
    </div>
</body>
</html>
