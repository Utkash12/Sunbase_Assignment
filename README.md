Customer CRUD Application

This project is a basic CRUD (Create, Read, Update, Delete) application for managing customer data, implemented using JSP and Servlets for the backend, and HTML/CSS/JSP for the frontend. The project includes authentication and features like sync, pagination, searching and some other functionalities.

Features:
Authentication: Implements basic authentication for secure access to the application, which is commented as per the requirements mentioned in the assignment.

Customer Management:
	Create a new customer
	Update existing customer details
	View a list of customers with sync, pagination, and searching
	Delete a customer

Pagination: The records are paginated with each page size being 10 records.
Sorting: The records are displayed in sorted order by default with sorting in ascending order on the basis of first name.
Searching: The user can search records based on different attributes, with the results containing all the records like the search attribute.
Sync: Clicking the sync button will fetch the records from the Sunbase API while using the authentication bearer token.
Add Customer: Opens a new page to enter all the details for adding a new customer to the database.
Edit: Provides the option to edit the details of a specific customer record.
Delete: Provides the option to delete a specific customer record.


Project Structure:

Frontend: HTML, CSS, and JSP render the UI and handle user interactions.
Backend: JSP and Servlets handle the server-side logic for CRUD operations, authentication, and session management.
Database: MySQL is used to store customer data.


Getting Started:

Prerequisites
To run the application, ensure the following software is installed:
	Java Development Kit (JDK 8+)
	Apache Tomcat (version 9 or later)
	MySQL
	Maven

Installation
Clone the repository:
https://github.com/your-repo/customer-crud-app

Import the project into your favorite IDE (e.g., Eclipse, IntelliJ or any other).

Set up the MySQL database:

Create a database named as Sunbase.

Run the below SQL script to create the required table:
CREATE TABLE customer (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    street VARCHAR(100),
    address VARCHAR(100),
    city VARCHAR(50),
    state VARCHAR(50),
    email VARCHAR(100),
    phone VARCHAR(15)
);

Configure the database connection in the hibernate.cfg.xml file.

Build the project and deploy the generated WAR file to Apache Tomcat.

Start the Tomcat server.

Access the application via your browser at:

http://localhost:8081/SunBaseAssignment/




