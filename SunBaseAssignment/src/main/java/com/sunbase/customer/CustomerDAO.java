package com.sunbase.customer;

import java.util.List;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class CustomerDAO {
    private SessionFactory sessionFactory;

    // Initializing SessionFactory when DAO is created
    public CustomerDAO() {
        sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Customer.class)
                .buildSessionFactory();
    }

    // For saving or updating the customer
    public void saveCustomer(Customer customer) {
        Session session = null;
        Transaction transaction = null;

        try {
            // Opening the new session then begining the transaction
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            // Either saving or updating customer object from  database
            session.saveOrUpdate(customer);

            // Commiting the transaction
            transaction.commit();
        } catch (Exception e) {
            // Rollback the transaction if any error occurs
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            // Ensuring session is closed for freeing up the resources
            if (session != null) {
                session.close();
            }
        }
    }

    // Searching for customers based on criteria
    public List<Customer> searchCustomers(String criteria, String queryStr) {
        Session session = null;
        Transaction transaction = null;
        List<Customer> customers = null;

        try {
            // Opening  new session then begining a transaction
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            // Building HQL query based on search criteria
            String hql = "from Customer where " + criteria + " like :query";
            Query<Customer> query = session.createQuery(hql, Customer.class)
                    .setParameter("query", "%" + queryStr + "%");

            // Executing the query and then retrieving the appropriate result
            customers = query.getResultList();

            // Commiting the transaction
            transaction.commit();
        } catch (Exception e) {
            // Rollback the transaction if any kind of error occurs
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            // Ensuring the session is closed for freeing up the resources
            if (session != null) {
                session.close();
            }
        }
        return customers;
    }

    
    public List<Customer> getAllCustomers() {
        Session session = null;
        Transaction transaction = null;
        List<Customer> customers = null;

        try {
            // Opening a new session and begin a transaction
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            // HQL query to fetch all records from the Customer table
            String hql = "from Customer";

            // Create the query with the HQL statement
            Query<Customer> query = session.createQuery(hql, Customer.class);

            // Execute the query and retrieve all records
            customers = query.getResultList();

            // Commit the transaction
            transaction.commit();
        } catch (Exception e) {
            // Rollback the transaction in case of an error
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            // Ensure the session is closed to free up resources
            if (session != null) {
                session.close();
            }
        }

        return customers;
    }

    // Get single customer by ID
    public Customer getCustomerById(int id) {
        Session session = null;
        Transaction transaction = null;
        Customer customer = null;

        try {
            // Open a new session and begin a transaction
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            // Retrieve the customer by ID
            customer = session.get(Customer.class, id);

            // Commit the transaction
            transaction.commit();
        } catch (Exception e) {
            // Rollback the transaction in case of an error
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            // Ensure the session is closed to free up resources
            if (session != null) {
                session.close();
            }
        }
        return customer;
    }

    // Delete customer by ID
    public void deleteCustomer(int id) {
        Session session = null;
        Transaction transaction = null;

        try {
            // Open a new session and begin a transaction
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            // Retrieve the customer by ID
            Customer customer = session.get(Customer.class, id);

            // If the customer exists, delete the customer
            if (customer != null) {
                session.delete(customer);
            }

            // Commit the transaction
            transaction.commit();
        } catch (Exception e) {
            // Rollback the transaction in case of an error
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            // Ensure the session is closed to free up resources
            if (session != null) {
                session.close();
            }
        }
    }

    // Close the SessionFactory when it's no longer needed
    public void closeSessionFactory() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
    
    // Method to update an existing customer
    public void updateCustomer(Customer customer) {
        Session session = null;
        Transaction transaction = null;

        try {
            // Open a new session and begin a transaction
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            session.update(customer);

            // Commit the transaction
            transaction.commit();
        } catch (Exception e) {
            // Rollback the transaction in case of an error
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            // Ensure the session is closed to free up resources
            if (session != null) {
                session.close();
            }
        }
    }

    // Making a boolean type function to check that a customer exists by email
    public boolean customerExists(String email) {
        Session session = null;
        Transaction transaction = null;
        List<Customer> results = null;

        try {
            // Opening the new session and then begining the transaction
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            String hql = "from Customer where email = :email";

            // Creating query by the help of HQL statement
            Query<Customer> query = session.createQuery(hql, Customer.class);
            query.setParameter("email", email);
            results  = query.list();

            // Commiting the transaction
            transaction.commit();
        } catch (Exception e) {
            // Rollback the transaction if any error occurs
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            // Ensuring session is closed for freeing up the resources
            if (session != null) {
                session.close();
            }
        }
        
        return !results.isEmpty();
    }
    

    // Making it for getting all customers with pagination, sorting, and searching
    public List<Customer> getAllCustomersPagination(int pageNumber, int pageSize, String sortField, String sortOrder) {
    	Session session = null;
        Transaction transaction = null;
        List<Customer> customers = null;

        try {
            // Open a new session and begin a transaction
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            // Base HQL query without any search criteria
            String hql = "from Customer";

            // Apply sorting if sortField is provided
            if (sortField != null && !sortField.isEmpty()) {
                hql += " order by " + sortField + " " + (sortOrder.equalsIgnoreCase("desc") ? "desc" : "asc");
            }

            // Create the query and apply pagination
            Query<Customer> query = session.createQuery(hql, Customer.class)
                .setFirstResult((pageNumber - 1) * pageSize) // Pagination: starting point
                .setMaxResults(pageSize); // Pagination: max results per page

            // Execute the query and retrieve the result
            customers = query.getResultList();

            // Commit the transaction
            transaction.commit();
        } catch (Exception e) {
            // Rollback transaction if any error occurs
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            // Ensure the session is closed to free up resources
            if (session != null) {
                session.close();
            }
        }
        return customers;
    }
    
 // Retrieving a paginated list of customers
    public List<Customer> getCustomers(int page, int pageSize) {
        Session session = null;
        Transaction transaction = null;
        List<Customer> customers = null;

        try {
            // Opening a new session and beginning a transaction
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            // Building HQL query for pagination
            String hql = "FROM Customer"; // Fetch all customers
            Query<Customer> query = session.createQuery(hql, Customer.class);

            // Setting pagination parameters
            query.setFirstResult((page - 1) * pageSize); // Offset
            query.setMaxResults(pageSize); // Limit

            // Executing the query and retrieving the result
            customers = query.getResultList();

            // Committing the transaction
            transaction.commit();
        } catch (Exception e) {
            // Rollback the transaction if an error occurs
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            // Ensuring the session is closed to free up resources
            if (session != null) {
                session.close();
            }
        }
        return customers;
    }

    // Getting the total count of customers for pagination
    public int getCustomerCount() {
        Session session = null;
        Transaction transaction = null;
        int count = 0;

        try {
            // Opening a new session and beginning a transaction
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            // Building HQL query to count the total number of customers
            String hql = "SELECT COUNT(*) FROM Customer";
            Query<Long> query = session.createQuery(hql, Long.class);
            count = query.uniqueResult().intValue(); // Retrieve the count

            // Committing the transaction
            transaction.commit();
        } catch (Exception e) {
            // Rollback the transaction if an error occurs
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            // Ensuring the session is closed to free up resources
            if (session != null) {
                session.close();
            }
        }
        return count;
    }

 // Getting all customers sorted by first name
    public List<Customer> getSortedCustomers(int page, int pageSize) {
        Session session = null;
        Transaction transaction = null;
        List<Customer> customers = null;

        try {
            // Opening a new session and beginning a transaction
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            // Building HQL query to fetch all customers sorted by first name with pagination
            String hql = "from Customer order by firstName asc"; // Modify as needed for sorting
            Query<Customer> query = session.createQuery(hql, Customer.class)
                    .setFirstResult((page - 1) * pageSize) // Set the starting point for pagination
                    .setMaxResults(pageSize); // Set the max number of results to return

            // Executing the query and retrieving the result
            customers = query.getResultList();

            // Committing the transaction
            transaction.commit();
        } catch (Exception e) {
            // Rolling back the transaction if any error occurs
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            // Ensuring the session is closed to free up resources
            if (session != null) {
                session.close();
            }
        }
        return customers;
    }

    
}

