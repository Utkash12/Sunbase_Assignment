<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.sunbase.customer.Customer"%>
<!DOCTYPE html>
<html>
<head>
<title>Customer List</title>
<style>
.pagination {
	display: flex;
	justify-content: center;
	margin-top: 20px;
}

.pagination a {
	margin: 0 5px;
	padding: 8px 12px;
	background-color: #007bff;
	color: white;
	border-radius: 5px;
	text-decoration: none;
}

.pagination a:hover {
	background-color: #0056b3;
}

.pagination span {
	margin: 0 5px;
	padding: 8px 12px;
}

/* General Styling */
body {
	font-family: 'Arial', sans-serif;
	background-color: #f8f9fa;
	color: #333;
	margin: 0;
	padding: 0;
}

h1 {
	text-align: center;
	margin-top: 30px;
	color: #007bff;
	font-size: 38px;
}

/* Container styling */
.container {
	width: 90%;
	max-width: 1200px;
	margin: 0 auto;
	padding: 20px;
	background-color: #fff;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	border-radius: 10px;
	margin-bottom: 40px;
}

/* Table Styling */
table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
}

table th, table td {
	padding: 12px;
	text-align: left;
	border: 1px solid #dee2e6;
}

th {
	background-color: #007bff;
	color: white;
}

td {
	background-color: #f9f9f9;
}

/* Action buttons */
.action-buttons {
	display: flex;
	gap: 10px;
}

.action-buttons form {
	display: inline-block;
}

/* Button Styling */
button, a.button {
	background-color: #007bff;
	color: white;
	border: none;
	padding: 8px 12px;
	text-decoration: none;
	border-radius: 5px;
	font-size: 14px;
	cursor: pointer;
}

button:hover, a.button:hover {
	background-color: #0056b3;
}

/* Search form styling */
form.search-form {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 20px;
}

form.search-form input[type="text"] {
	padding: 8px;
	font-size: 14px;
	border: 1px solid #ccc;
	border-radius: 5px;
	width: 300px;
}

form.search-form select {
	padding: 8px;
	font-size: 14px;
	border: 1px solid #ccc;
	border-radius: 5px;
}

form.search-form button {
	margin-left: 10px;
}

/* Sync Customers Button */
.sync-customers {
	margin-top: 20px;
	text-align: center;
}

.sync-customers button {
	padding: 10px 20px;
	background-color: #28a745;
}

.sync-customers button:hover {
	background-color: #218838;
}

/* No customers message */
.no-customers {
	text-align: center;
	padding: 20px;
	color: #666;
}
</style>
</head>
<body>
	<div class="container">
		
		<h1>Customer List</h1>

		<!-- Search form -->
		<form action="customer" method="get" class="search-form">
			<a href="add-customer.jsp" class="button">Add Customer</a>
			<div>
				<select name="searchCriteria">
					<option value="firstName">First Name</option>
					<option value="city">City</option>
					<option value="email">Email</option>
					<option value="phone">Phone</option>
				</select> 
				<input type="text" name="searchQuery" placeholder="Search...">
				<button type="submit" name="action" value="search">Search</button>
			</div>
		</form>

		<!-- Customer table -->
		<table>
			<thead>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Street</th>
					<th>Address</th>
					<th>City</th>
					<th>State</th>
					<th>Email</th>
					<th>Phone</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<%
                    List<Customer> customers = (List<Customer>) request.getAttribute("customerList");
                    if (customers != null && !customers.isEmpty()) {
                        for (Customer customer : customers) {
                %>
				<tr>
					<td><%= customer.getFirstName() %></td>
					<td><%= customer.getLastName() %></td>
					<td><%= customer.getStreet() %></td>
					<td><%= customer.getAddress() %></td>
					<td><%= customer.getCity() %></td>
					<td><%= customer.getState() %></td>
					<td><%= customer.getEmail() %></td>
					<td><%= customer.getPhone() %></td>
					<td class="action-buttons">
						<form action="customer" method="post" style="display: inline;">
							<input type="hidden" name="id" value="<%= customer.getId() %>">
							<button type="submit" name="method" value="delete">Delete</button>
						</form> 
						<a href="update-customer.jsp?id=<%= customer.getId() %>" class="button">Edit</a>
					</td>
				</tr>
				<%
                        }
                    } else {
                %>
				<tr>
					<td colspan="9" class="no-customers">No customers found.</td>
				</tr>
				<%
                    }
                %>
			</tbody>
		</table>

		<!-- Add below the table -->
		<div class="pagination">
			<%
        // Handle potential NullPointerExceptions
        Integer currentPage = (Integer) request.getAttribute("currentPage");
        Integer totalPages = (Integer) request.getAttribute("totalPages");

        // Set default values if they are null
        if (currentPage == null) {
            currentPage = 1; // Default to first page if not set
        }
        if (totalPages == null) {
            totalPages = 1; // Default to 1 page if not set
        }

        if (totalPages > 1) {
            for (int i = 1; i <= totalPages; i++) {
                if (i == currentPage) {
    %>
			<span><strong><%= i %></strong></span>
			<%
                } else {
    %>
			<a href="customer?page=<%= i %>"><%= i %></a>
			<%
                }
            }
        }
    %>
		</div>

		<!-- Sync customers button -->
		<div class="sync-customers">
			<form action="sync-customers" method="post">
				<button type="submit">Sync Customers</button>
			</form>
		</div>
	</div>
	<!-- Redirect Button -->
<div class="sync-customers" style="text-align: center; margin-top: 20px;">
    <button onclick="window.location.href='customer?action=list';">View All Customers</button>
</div>
	
</body>
</html>


<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.sunbase.customer.Customer"%>
<!DOCTYPE html>
<html>
<head>
<title>Customer List</title>
<style>
.pagination {
	display: flex;
	justify-content: center;
	margin-top: 20px;
}

.pagination a {
	margin: 0 5px;
	padding: 8px 12px;
	background-color: #007bff;
	color: white;
	border-radius: 5px;
	text-decoration: none;
}

.pagination a:hover {
	background-color: #0056b3;
}

.pagination span {
	margin: 0 5px;
	padding: 8px 12px;
}

/* General Styling */
body {
	font-family: 'Arial', sans-serif;
	background-color: #f8f9fa;
	color: #333;
	margin: 0;
	padding: 0;
}

h2 {
	text-align: center;
	margin-top: 30px;
	color: #007bff;
	font-size: 28px;
}

/* Container styling */
.container {
	width: 90%;
	max-width: 1200px;
	margin: 0 auto;
	padding: 20px;
	background-color: #fff;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	border-radius: 10px;
	margin-bottom: 40px;
}

/* Table Styling */
table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
}

table th, table td {
	padding: 12px;
	text-align: left;
	border: 1px solid #dee2e6;
}

th {
	background-color: #007bff;
	color: white;
}

td {
	background-color: #f9f9f9;
}

/* Action buttons */
.action-buttons {
	display: flex;
	gap: 10px;
}

.action-buttons form {
	display: inline-block;
}

/* Button Styling */
button, a.button {
	background-color: #007bff;
	color: white;
	border: none;
	padding: 8px 12px;
	text-decoration: none;
	border-radius: 5px;
	font-size: 14px;
	cursor: pointer;
}

button:hover, a.button:hover {
	background-color: #0056b3;
}

/* Search form styling */
form.search-form {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 20px;
}

form.search-form input[type="text"] {
	padding: 8px;
	font-size: 14px;
	border: 1px solid #ccc;
	border-radius: 5px;
	width: 300px;
}

form.search-form select {
	padding: 8px;
	font-size: 14px;
	border: 1px solid #ccc;
	border-radius: 5px;
}

form.search-form button {
	margin-left: 10px;
}

/* Sync Customers Button */
.sync-customers {
	margin-top: 20px;
	text-align: center;
}

.sync-customers button {
	padding: 10px 20px;
	background-color: #28a745;
}

.sync-customers button:hover {
	background-color: #218838;
}

/* No customers message */
.no-customers {
	text-align: center;
	padding: 20px;
	color: #666;
}
</style>
</head>
<body>
	<div class="container">
		<h2>Customer List</h2>

		<!-- Search form -->
		<form action="customer" method="get" class="search-form">
			<a href="add-customer.jsp" class="button">Add Customer</a>
			<div>
				<select name="searchCriteria">
					<option value="firstName">First Name</option>
					<option value="city">City</option>
					<option value="email">Email</option>
					<option value="phone">Phone</option>
				</select> <input type="text" name="searchQuery" placeholder="Search...">
				<button type="submit" name="action" value="search">Search</button>
			</div>
		</form>

		<!-- Customer table -->
		<table>
			<thead>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Street</th>
					<th>Address</th>
					<th>City</th>
					<th>State</th>
					<th>Email</th>
					<th>Phone</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<%
                    List<Customer> customers = (List<Customer>) request.getAttribute("customerList");
                    if (customers != null && !customers.isEmpty()) {
                        for (Customer customer : customers) {
                %>
				<tr>
					<td><%= customer.getFirstName() %></td>
					<td><%= customer.getLastName() %></td>
					<td><%= customer.getStreet() %></td>
					<td><%= customer.getAddress() %></td>
					<td><%= customer.getCity() %></td>
					<td><%= customer.getState() %></td>
					<td><%= customer.getEmail() %></td>
					<td><%= customer.getPhone() %></td>
					<td class="action-buttons">
						<form action="customer" method="post" style="display: inline;">
							<input type="hidden" name="id" value="<%= customer.getId() %>">
							<button type="submit" name="method" value="delete">Delete</button>
						</form> <a href="update-customer.jsp?id=<%= customer.getId() %>"
						class="button">Edit</a>
					</td>
				</tr>
				<%
                        }
                    } else {
                %>
				<tr>
					<td colspan="9" class="no-customers">No customers found.</td>
				</tr>
				<%
                    }
                %>
			</tbody>
		</table>

		<!-- Add below the table -->
		<div class="pagination">
			<%
        int currentPage = (Integer) request.getAttribute("currentPage");
        int totalPages = (Integer) request.getAttribute("totalPages");

        if (totalPages > 1) {
            for (int i = 1; i <= totalPages; i++) {
                if (i == currentPage) {
    %>
			<span><strong><%= i %></strong></span>
			<%
                } else {
    %>
			<a href="customer?page=<%= i %>"><%= i %></a>
			<%
                }
            }
        }
    %>
		</div>


		<!-- Sync customers button -->
		<div class="sync-customers">
			<form action="sync-customers" method="post">
				<button type="submit">Sync Customers</button>
			</form>
		</div>
	</div>
</body>
</html> --%>



<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.sunbase.customer.Customer" %>
<!DOCTYPE html>
<html>
<head>
    <title>Customer List</title>
    <style>
        /* General Styling */
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f8f9fa;
            color: #333;
            margin: 0;
            padding: 0;
        }

        h2 {
            text-align: center;
            margin-top: 30px;
            color: #007bff;
            font-size: 28px;
        }

        /* Container styling */
        .container {
            width: 90%;
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
            margin-bottom: 40px;
        }

        /* Table Styling */
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        table th, table td {
            padding: 12px;
            text-align: left;
            border: 1px solid #dee2e6;
        }

        th {
            background-color: #007bff;
            color: white;
        }

        td {
            background-color: #f9f9f9;
        }

        /* Action buttons */
        .action-buttons {
            display: flex;
            gap: 10px;
        }

        .action-buttons form {
            display: inline-block;
        }

        /* Button Styling */
        button, a.button {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 8px 12px;
            text-decoration: none;
            border-radius: 5px;
            font-size: 14px;
            cursor: pointer;
        }

        button:hover, a.button:hover {
            background-color: #0056b3;
        }

        /* Search form styling */
        form.search-form {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
        }

        form.search-form input[type="text"] {
            padding: 8px;
            font-size: 14px;
            border: 1px solid #ccc;
            border-radius: 5px;
            width: 300px;
        }

        form.search-form select {
            padding: 8px;
            font-size: 14px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        form.search-form button {
            margin-left: 10px;
        }

        /* Sync Customers Button */
        .sync-customers {
            margin-top: 20px;
            text-align: center;
        }

        .sync-customers button {
            padding: 10px 20px;
            background-color: #28a745;
        }

        .sync-customers button:hover {
            background-color: #218838;
        }

        /* No customers message */
        .no-customers {
            text-align: center;
            padding: 20px;
            color: #666;
        }

    </style>
</head>
<body>
    <div class="container">
        <h2>Customer List</h2>
        
        <!-- Search form -->
        <form action="customer" method="get" class="search-form">
            <a href="add-customer.jsp" class="button">Add Customer</a>
            <div>
                <select name="searchCriteria">
                    <option value="firstName">First Name</option>
                    <option value="city">City</option>
                    <option value="email">Email</option>
                    <option value="phone">Phone</option>
                </select>
                <input type="text" name="searchQuery" placeholder="Search...">
                <button type="submit" name="action" value="search">Search</button>
            </div>
        </form>

        <!-- Customer table -->
        <table>
            <thead>
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Street</th>
                    <th>Address</th>
                    <th>City</th>
                    <th>State</th>
                    <th>Email</th>
                    <th>Phone</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Customer> customers = (List<Customer>) request.getAttribute("customerList");
                    if (customers != null && !customers.isEmpty()) {
                        for (Customer customer : customers) {
                %>
                    <tr>
                        <td><%= customer.getFirstName() %></td>
                        <td><%= customer.getLastName() %></td>
                        <td><%= customer.getStreet() %></td>
                        <td><%= customer.getAddress() %></td>
                        <td><%= customer.getCity() %></td>
                        <td><%= customer.getState() %></td>
                        <td><%= customer.getEmail() %></td>
                        <td><%= customer.getPhone() %></td>
                        <td class="action-buttons">
                            <form action="customer" method="post" style="display:inline;">
                                <input type="hidden" name="id" value="<%= customer.getId() %>">
                                <button type="submit" name="method" value="delete">Delete</button>
                            </form>
                            <a href="update-customer.jsp?id=<%= customer.getId() %>" class="button">Edit</a>
                        </td>
                    </tr>
                <%
                        }
                    } else {
                %>
                    <tr>
                        <td colspan="9" class="no-customers">No customers found.</td>
                    </tr>
                <%
                    }
                %>
            </tbody>
        </table>

        <!-- Sync customers button -->
        <div class="sync-customers">
            <form action="sync-customers" method="post">
                <button type="submit">Sync Customers</button>
            </form>
        </div>
    </div>
</body>
</html>
 --%>