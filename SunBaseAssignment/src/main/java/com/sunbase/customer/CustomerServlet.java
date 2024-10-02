package com.sunbase.customer;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.google.gson.Gson;

// Thisservlet is made for handling the requests in /customer URL
@WebServlet("/customer")
public class CustomerServlet extends HttpServlet {
    
    //Unique identifier using during the serialization of this class version
    private static final long serialVersionUID = 1L;

    //An instance of CustomerDAO that helps in interacting with data of the customer
    private CustomerDAO customerDAO = new CustomerDAO();
    
    // It use to handle HTTP GET requests
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the action parameter from the request to determine what to do
        String action = request.getParameter("action");

        //IUsed for search action to manage the searching operation else list of all the customers.
        if ("search".equals(action)) {
            performSearch(request, response);
        } else if ("list".equals(action) || action == null) {
            getAllCustomers(request, response);
        }
    }
    //Used to perform searching for customers based on search criteria and the query 
    private void performSearch(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	
    	String searchCriteria = request.getParameter("searchCriteria");
        String searchQuery = request.getParameter("searchQuery");

        // Perform the search operation
        List<Customer> customers = customerDAO.searchCustomers(searchCriteria, searchQuery);

        // Set the list of customers as a request attribute
        request.setAttribute("customerList", customers);
        
        // Calculate total number of customers for pagination
        int totalCustomers = customerDAO.getCustomerCount();
        request.setAttribute("totalCustomers", totalCustomers);

        // Forward the request and response to customer-list.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("customer-list.jsp");
        dispatcher.forward(request, response);
        
    }

    //It helps in retrieving all the customers from database and forward them to list page
    private void getAllCustomers(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	
    	int page = 1; // current page
        int pageSize = 10; // number of customers per page
        
        String pageParam = request.getParameter("page");
        if (pageParam != null) {
            try {
                page = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                page = 1; // Default to page 1 if the parameter is invalid
            }
        }

        // Get total customer count
        int totalCustomers = customerDAO.getCustomerCount();
        // Get paginated list of customers sorted by first name
        List<Customer> customers = customerDAO.getSortedCustomers(page, pageSize);

        // Set attributes for pagination
        request.setAttribute("customerList", customers);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", (int) Math.ceil((double) totalCustomers / pageSize));
        
        // Forward to JSP page
        RequestDispatcher dispatcher = request.getRequestDispatcher("customer-list.jsp");
        dispatcher.forward(request, response);
        
		/*
		 * int page = 1; // current page int pageSize = 10; // number of customers per
		 * page
		 * 
		 * String pageParam = request.getParameter("page"); if (pageParam != null) { try
		 * { page = Integer.parseInt(pageParam); } catch (NumberFormatException e) { //
		 * Default to page 1 if the parameter is invalid page = 1; } }
		 * 
		 * // Get total customer count int totalCustomers =
		 * customerDAO.getCustomerCount(); // Get paginated list of customers
		 * List<Customer> customers = customerDAO.getCustomers(page, pageSize);
		 * 
		 * // Set attributes for pagination request.setAttribute("customerList",
		 * customers); request.setAttribute("currentPage", page);
		 * request.setAttribute("totalPages", (int) Math.ceil((double) totalCustomers /
		 * pageSize));
		 * 
		 * // Forward to JSP page RequestDispatcher dispatcher =
		 * request.getRequestDispatcher("customer-list.jsp");
		 * dispatcher.forward(request, response);
		 */
		
		 
    }

    //It helps in retrieving customer by the help of their ID and then use to send their data in JSON format
    private void getCustomerById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //It helps in parsing the customer ID from the request
        int id = Integer.parseInt(request.getParameter("id"));

        //It helps in fetching customer from the database by the help of customerDAO
        Customer customer = customerDAO.getCustomerById(id);
        
        //It helps in returning the data in JSON format, if the customer use to exist else it use to return a 404 status
        if (customer != null) {
            response.setContentType("application/json");
            response.getWriter().write(new Gson().toJson(customer));  // Converts customer object to JSON
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);  // Customer not found
        }
    }

    //It helps in handling the HTTP POST requests
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //It helps in determineing which type of operation is to be performed on the basis of method parameter
        String method = request.getParameter("method");

        //It helps in performing the corresponding actions  create,update or delete
        if ("delete".equals(method)) {
            deleteCustomer(request, response);
        } else if ("create".equals(method)) {
            createCustomer(request, response);
        } else if ("update".equals(method)) {
            updateCustomer(request, response);
        }
    }

    //It helps in deleting the customer on the basis of their ID and then redirects to the customer list
    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Parse the customer ID from the request
        int id = Integer.parseInt(request.getParameter("id"));

        //It helps in deleting the customer from the database
        customerDAO.deleteCustomer(id);

        //It helps in redirecting back to the list of customers after the deletion operation is completed
        response.sendRedirect("customer?action=list");
    }

    //It helps in creating the new customer using data provided in the request
    private void createCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Create a new Customer object and populate it with data from the request
        Customer customer = new Customer();
        customer.setFirstName(request.getParameter("first_name"));
        customer.setLastName(request.getParameter("last_name"));
        customer.setStreet(request.getParameter("street"));
        customer.setAddress(request.getParameter("address"));
        customer.setCity(request.getParameter("city"));
        customer.setState(request.getParameter("state"));
        customer.setEmail(request.getParameter("email"));
        customer.setPhone(request.getParameter("phone"));

        //It helps in saving customer in the database
        customerDAO.saveCustomer(customer);

        // It helps in redirecting back to the list of the customers after the creation
        response.sendRedirect("customer?action=list");
    }

    //It helps in updating an already existing customer with new information by the help of the request
    private void updateCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //It helps in parsing ID of the customer by the help of the request
        int id = Integer.parseInt(request.getParameter("id"));

        //It helps in fetching customer from database
        Customer customer = customerDAO.getCustomerById(id);

        // If the customer is already there then it use to update its details and then save the changes
        if (customer != null) {
            customer.setFirstName(request.getParameter("first_name"));
            customer.setLastName(request.getParameter("last_name"));
            customer.setStreet(request.getParameter("street"));
            customer.setAddress(request.getParameter("address"));
            customer.setCity(request.getParameter("city"));
            customer.setState(request.getParameter("state"));
            customer.setEmail(request.getParameter("email"));
            customer.setPhone(request.getParameter("phone"));

            //It helps in saving the updated customer details
            customerDAO.saveCustomer(customer);

            //It helps in redirecting back to the list of customers after updation is done
            response.sendRedirect("customer?action=list");
        } else {
            // It use to return a 404 error, if the customer doesn't exist
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
