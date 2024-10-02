package com.sunbase.customer;

import com.sunbase.customer.Customer;
import com.sunbase.customer.CustomerDAO; 
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

//Its a servlet which helps to synchronize customers from a remote API to local database
@WebServlet("/sync-customers")
public class SyncCustomersServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    //These are the URL for authentication and for fetching up the data of the customerfrom the remote API
    private static final String AUTH_URL = "https://qa.sunbasedata.com/sunbase/portal/api/assignment_auth.jsp";
    private static final String CUSTOMER_LIST_URL = "https://qa.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=get_customer_list";
    
    //These are the credentials for authentication by the help of the remote API
    private static final String LOGIN_ID = "test@sunbasedata.com";
    private static final String PASSWORD = "Test@123";

    //It use to handle POST request for syncing customer data
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //It use to perform authentication work and then retrieves the token
            String token = authenticateUser();

            if (token != null) {
                //It helps in fetching the customer list by the help of authenticated token
                List<Customer> customers = fetchCustomerList(token);

                //It helps in initializing the DAO for saving or updating customers in the local database
                CustomerDAO customerDAO = new CustomerDAO();

                // Loop through each customer for updating if already exists else use to add a new entry
                for (Customer customer : customers) {
                    if (customerDAO.customerExists(customer.getEmail())) {
                        customerDAO.updateCustomer(customer); // Update existing customer
                    } else {
                        customerDAO.saveCustomer(customer); // Save new customer
                    }
                }

                // Redirect back to the customer list page after successful synchronization
                response.sendRedirect("customer?action=list");
            } else {
                // If authentication fails, display an error message
                request.setAttribute("errorMessage", "Authentication failed.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } catch (Exception e) {
            // Handle any exceptions that occur during the synchronization process
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred while syncing customers.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    // Method to authenticate the user by sending credentials and receiving a token
    private String authenticateUser() throws IOException {
        // Open a connection to the authentication URL
        URL url = new URL(AUTH_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        
        // Set the request method and headers
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true); // Enable writing to the connection

        // Prepare the JSON body with login credentials
        String body = "{\"login_id\" : \"" + LOGIN_ID + "\", \"password\" : \"" + PASSWORD + "\"}";
        conn.getOutputStream().write(body.getBytes("UTF-8")); // Send the request body

        // Check if authentication was successful (HTTP 200)
        if (conn.getResponseCode() == 200) {
            // Read the response from the server
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;

            // Append each line of the response to the StringBuilder
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Parse the response JSON to extract the token
            Gson gson = new Gson();
            String token = gson.fromJson(response.toString(), JsonObject.class).get("access_token").getAsString();
            return token; // Return the authentication token
        }
        return null; // Return null if authentication failed
    }

    // Method to fetch the customer list from the API using the authentication token
    private List<Customer> fetchCustomerList(String token) throws IOException {
        // Open a connection to the customer list URL
        URL url = new URL(CUSTOMER_LIST_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        
        // Set the request method and authorization header with the token
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", "Bearer " + token);

        // Check if the request was successful (HTTP 200)
        if (conn.getResponseCode() == 200) {
            // Read the customer list response
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;

            // Append each line of the response to the StringBuilder
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Parse the JSON response into a list of Customer objects
            Gson gson = new Gson();
            return gson.fromJson(response.toString(), new TypeToken<List<Customer>>() {}.getType());
        }
        return null; // Return null if fetching the customer list failed
    }
}
