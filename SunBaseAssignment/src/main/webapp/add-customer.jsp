<!--  <form action="customer" method="post">
    <input type="hidden" name="action" value="create">
    First Name: <input type="text" name="first_name" required><br>
    Last Name: <input type="text" name="last_name" required><br>
    Street: <input type="text" name="street" required><br>
    Address: <input type="text" name="address" required><br>
    City: <input type="text" name="city" required><br>
    State: <input type="text" name="state" required><br>
    Email: <input type="email" name="email" required><br>
    Phone: <input type="text" name="phone" required><br>
    <button type="submit">Add Customer</button>
</form>-->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Customer</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .form-container {
            width: 400px;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }

        h2 {
            text-align: center;
            color: #333;
        }

        .form-group {
            margin-bottom: 15px;
        }

        label {
            display: block;
            font-size: 14px;
            color: #555;
            margin-bottom: 5px;
        }

        input[type="text"], input[type="email"] {
            width: 100%;
            padding: 10px;
            font-size: 14px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        input[type="text"]:focus, input[type="email"]:focus {
            border-color: #4CAF50;
            outline: none;
        }

        .submit-btn {
            display: block;
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            font-size: 16px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .submit-btn:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

<div class="form-container">
    <h2>Add New Customer</h2>
    <form action="customer" method="post">
        <input type="hidden" name="method" value="create">

        <div class="form-group">
            <label for="first_name">First Name:</label>
            <input type="text" id="first_name" name="first_name" required>
        </div>

        <div class="form-group">
            <label for="last_name">Last Name:</label>
            <input type="text" id="last_name" name="last_name" required>
        </div>

        <div class="form-group">
            <label for="street">Street:</label>
            <input type="text" id="street" name="street" required>
        </div>

        <div class="form-group">
            <label for="address">Address:</label>
            <input type="text" id="address" name="address" required>
        </div>

        <div class="form-group">
            <label for="city">City:</label>
            <input type="text" id="city" name="city" required>
        </div>

        <div class="form-group">
            <label for="state">State:</label>
            <input type="text" id="state" name="state" required>
        </div>

        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
        </div>

        <div class="form-group">
            <label for="phone">Phone:</label>
            <input type="text" id="phone" name="phone" required>
        </div>

        <button type="submit" class="submit-btn">Add Customer</button>
    </form>
</div>

</body>
</html>

