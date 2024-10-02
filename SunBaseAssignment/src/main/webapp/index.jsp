<!-- form action="login" method="post">
    Username: <input type="text" name="username" required><br>
    Password: <input type="password" name="password" required><br>
    <button type="submit">Login</button>
</form>-->
<!--<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>-->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sunbase Company - Login</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            background: linear-gradient(to bottom right, #FFB74D, #F57C00);
            font-family: 'Arial', sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .login-container {
            background-color: #ffffff;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
            text-align: center;
            max-width: 400px;
            width: 100%;
        }

        .login-container h2 {
            color: #F57C00;
            margin-bottom: 20px;
            font-size: 28px;
        }

        .login-container input[type="text"], .login-container input[type="password"] {
            width: 100%;
            padding: 15px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
        }

        .login-container button {
            width: 100%;
            background-color: #F57C00;
            color: white;
            padding: 15px;
            font-size: 18px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .login-container button:hover {
            background-color: #FFB74D;
        }

        .login-container p {
            margin-top: 20px;
            color: #666;
        }

        .login-container a {
            color: #F57C00;
            text-decoration: none;
        }

        /* .logo img {
            width: 80px;
            margin-bottom: 20px;
        } */
    </style>
</head>
<body>
    <div class="login-container">
        <!-- <div class="logo">
            <img src="sunbase_logo.png" alt="Sunbase Logo">
        </div> -->
        <h2>Welcome to Sunbase</h2>
        <form action="loginServlet" method="post">
            <input type="text" name="username" placeholder="Username not required">
            <input type="password" name="password" placeholder="Password not required" >
            <button type="submit">Login</button>
        </form>
        <p>Don't need username/password to login as per the assignment requirements </p>
    </div>
</body>
</html>

