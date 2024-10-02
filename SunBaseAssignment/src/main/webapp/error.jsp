<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Error Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 20px;
        }
        .error-container {
            background-color: white;
            border-radius: 5px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
            max-width: 600px;
            margin: 0 auto;
        }
        h1 {
            color: #e74c3c;
        }
        p {
            color: #333;
        }
        a {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 15px;
            background-color: #3498db;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
        a:hover {
            background-color: #2980b9;
        }
    </style>
</head>
<body>

<div class="error-container">
    <h1>Error Occurred</h1>
    <p>
        <% 
            // Get the error message from request attribute
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null) {
        %>
                <strong>Error:</strong> <%= errorMessage %>
        <% 
            } else { 
        %>
                An unexpected error has occurred. Please try again later.
        <% 
            } 
        %>
    </p>
    <a href="customers">Go Back to Customer List</a>
</div>

</body>
</html>
