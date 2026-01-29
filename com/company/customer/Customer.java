package com.company.customer;

public class Customer {
    private int customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String country;

    public Customer() {
        this.customerId = 0;
        this.firstName = "UNKNOWN";
        this.lastName = "UNKNOWN";
        this.email = "UNKNOWN";
        this.phone = "UNKNOWN";
        this.country = "UNKNOWN";
    }

    public Customer(int customerId, String firstName, String lastName) {
        this(customerId, firstName, lastName, "NOT_PROVIDED", "NOT_PROVIDED", "NOT_PROVIDED");
    }

    public Customer(int customerId, String firstName, String lastName, 
                    String email, String phone, String country) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.country = country;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getCountry() {
        return country;
    }

    public String getFullName() {
        String fullName = firstName.concat(" ").concat(lastName);
        return fullName.toUpperCase();
    }

    public void displayCustomer() {
        System.out.println("Customer ID: " + customerId);
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
        System.out.println("Country: " + country);
        
        if (firstName.length() > 0) {
            System.out.println("First character of first name: " + firstName.charAt(0));
        }
        if (lastName.length() > 0) {
            System.out.println("First character of last name: " + lastName.charAt(0));
        }
    }
}