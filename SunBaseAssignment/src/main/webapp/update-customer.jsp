<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.sunbase.customer.Customer" %>
<%@ page import="com.sunbase.customer.CustomerDAO" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="javax.servlet.http.HttpServletResponse" %>
<%@ page import="javax.servlet.RequestDispatcher" %>
<%@ page import="javax.servlet.ServletException" %>
<%@ page import="java.io.IOException" %>

<%
    int customerId = Integer.parseInt(request.getParameter("id"));
    CustomerDAO customerDAO = new CustomerDAO();
    Customer customer = customerDAO.getCustomerById(customerId);
%>

<!DOCTYPE html>
<html>
<head>
    <title>Update Customer</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 0;
            color: #333;
        }

        .container {
            width: 90%;
            max-width: 600px;
            margin: 50px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h2 {
            text-align: center;
            color: #007bff;
        }

        form {
            display: flex;
            flex-direction: column;
            gap: 15px;
        }

        label {
            font-weight: bold;
        }

        input[type="text"], input[type="email"], input[type="tel"] {
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 5px;
            width: 100%;
        }

        input[type="submit"] {
            padding: 10px;
            font-size: 16px;
            color: #fff;
            background-color: #007bff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }

        .cancel-btn {
            text-align: center;
            margin-top: 20px;
        }

        .cancel-btn a {
            color: #007bff;
            text-decoration: none;
            font-weight: bold;
        }

        .cancel-btn a:hover {
            text-decoration: underline;
        }

        @media (max-width: 768px) {
            .container {
                width: 95%;
                padding: 15px;
            }

            h2 {
                font-size: 24px;
            }
        }
    </style>
</head>
<body>

    <div class="container">
        <h2>Update Customer</h2>

        <form action="customer" method="post">
            <input type="hidden" name="method" value="update">
            <input type="hidden" name="id" value="<%= customer.getId() %>">

            <label for="first_name">First Name:</label>
            <input type="text" id="first_name" name="first_name" value="<%= customer.getFirstName() %>" required>

            <label for="last_name">Last Name:</label>
            <input type="text" id="last_name" name="last_name" value="<%= customer.getLastName() %>" required>

            <label for="street">Street:</label>
            <input type="text" id="street" name="street" value="<%= customer.getStreet() %>" required>

            <label for="address">Address:</label>
            <input type="text" id="address" name="address" value="<%= customer.getAddress() %>" required>

            <label for="city">City:</label>
            <input type="text" id="city" name="city" value="<%= customer.getCity() %>" required>

            <label for="state">State:</label>
            <input type="text" id="state" name="state" value="<%= customer.getState() %>" required>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" value="<%= customer.getEmail() %>" required>

            <label for="phone">Phone:</label>
            <input type="tel" id="phone" name="phone" value="<%= customer.getPhone() %>" required>

            <input type="submit" value="Update Customer">
        </form>

        <div class="cancel-btn">
            <a href="customer?action=list">Cancel</a>
        </div>
    </div>

</body>
</html>
