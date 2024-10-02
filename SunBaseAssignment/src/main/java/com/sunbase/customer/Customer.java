package com.sunbase.customer;


import javax.persistence.*;

// Marking the class as an entity to be mapped to database table
@Entity
// Specifying table in a database which use to tell that this entity is mapped to
@Table(name = "customer")
public class Customer {

    // Specifying it as the primary key field
    @Id
    // Defining how primary key value is generated i.e auto-incremented
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Mapping the field to the coolumn naming "first_name" in table
    @Column(name = "first_name")
    private String firstName;

    // Mapping the field to the column naming "last_name" in table
    @Column(name = "last_name")
    private String lastName;

    // Field is directly mapped to database column without using the explicit annotation @Column
    private String street;

    // Making field for customer address
    private String address;

    // Making field for city where customer lives
    private String city;

    // Making field for state where the customer belongs to
    private String state;

    // Making field for email address of the customer
    private String email;

    // Making field for phone number of the customer
    private String phone;

    // Making getter for the id 
    public int getId() {
        return id;
    }

    // Making setter for the id 
    public void setId(int id) {
        this.id = id;
    }

    // Making getter for the firstName 
    public String getFirstName() {
        return firstName;
    }

    // Making setter for the firstName
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Making getter for the lastName 
    public String getLastName() {
        return lastName;
    }

    // Making setter for the lastName 
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Making getter for the street 
    public String getStreet() {
        return street;
    }

    // Making setter for the street
    public void setStreet(String street) {
        this.street = street;
    }

    // Making getter for the address
    public String getAddress() {
        return address;
    }

    // Making setter for the address
    public void setAddress(String address) {
        this.address = address;
    }

    // Making getter for the city
    public String getCity() {
        return city;
    }

    // Making setter for the city 
    public void setCity(String city) {
        this.city = city;
    }

    // Making getter for the state
    public String getState() {
        return state;
    }

    // Making setter for the state 
    public void setState(String state) {
        this.state = state;
    }

    // Making getter for the email
    public String getEmail() {
        return email;
    }

    // Making setter for the email 
    public void setEmail(String email) {
        this.email = email;
    }

    // Making getter for the phone 
    public String getPhone() {
        return phone;
    }

    // Making setter for the phone 
    public void setPhone(String phone) {
        this.phone = phone;
    }

    // Making toString method to represent Customer object in a form of string
    @Override
    public String toString() {
        return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", street=" + street
                + ", address=" + address + ", city=" + city + ", state=" + state + ", email=" + email + ", phone="
                + phone + "]";
    }
}
