package com.company.customer;

public class CustomerApp {
    public static void main(String[] args) {
        System.out.println("=== CUSTOMER ONBOARDING SYSTEM ===\n");
        
        System.out.println("=== Creating Customers ===");
        
        Customer customer1 = new Customer();
        System.out.println("Customer 1 created with default constructor");
        
        Customer customer2 = new Customer(101, "Rahul", "Sharma");
        System.out.println("Customer 2 created with partial constructor (chained)");
        
        Customer customer3 = new Customer(102, "Priya", "Patel", 
                                         "priya.patel@company.com", "9876543210", "India");
        System.out.println("Customer 3 created with full constructor");
        
        Customer customer4 = new Customer(103, "John", "Doe", 
                                         "john.doe@gmail.com", "5551234567", "USA");
        
        System.out.println("\n" + "=".repeat(50));
        
        displayAndValidateCustomer(customer2, "Customer 2 (Rahul Sharma)");
        displayAndValidateCustomer(customer3, "Customer 3 (Priya Patel)");
        displayAndValidateCustomer(customer4, "Customer 4 (John Doe)");
        
        System.out.println("\n=== Demonstrating String Immutability ===");
        demonstrateStringImmutability();
        
        System.out.println("\n=== Name Analysis Examples ===");
        CustomerValidator.analyzeName("Rahul Sharma");
        CustomerValidator.analyzeName("Priya Patel");
        CustomerValidator.analyzeName("John Michael Doe");
        
        System.out.println("\n=== Program Completed Successfully ===");
    }
    
    private static void displayAndValidateCustomer(Customer customer, String title) {
        System.out.println("\n" + title);
        System.out.println("-".repeat(30));
        
        String fullName = customer.getFullName();
        System.out.println("Full Name: " + fullName);
        
        boolean emailValid = CustomerValidator.isValidEmail(customer.getEmail());
        boolean phoneValid = CustomerValidator.isValidPhone(customer.getPhone());
        boolean countryAllowed = CustomerValidator.isCountryAllowed(customer.getCountry());
        
        System.out.println("Email Valid: " + emailValid + 
                          " (" + customer.getEmail() + ")");
        System.out.println("Phone Valid: " + phoneValid + 
                          " (" + customer.getPhone() + ")");
        System.out.println("Country Allowed: " + countryAllowed + 
                          " (" + customer.getCountry() + ")");
        
        customer.displayCustomer();
        System.out.println("Name Analysis Completed");
        System.out.println("-".repeat(30));
    }
    
    private static void demonstrateStringImmutability() {
        String original = "Hello World";
        System.out.println("Original String: \"" + original + "\"");
        System.out.println("Original hashcode: " + System.identityHashCode(original));
        
        String upperCase = original.toUpperCase();
        System.out.println("\nAfter toUpperCase(): \"" + upperCase + "\"");
        System.out.println("New string hashcode: " + System.identityHashCode(upperCase));
        System.out.println("Original unchanged: \"" + original + "\"");
        
        String replaced = original.replace('o', '0');
        System.out.println("\nAfter replace('o', '0'): \"" + replaced + "\"");
        System.out.println("New string hashcode: " + System.identityHashCode(replaced));
        System.out.println("Original unchanged: \"" + original + "\"");
        
        String concated = original.concat("!!!");
        System.out.println("\nAfter concat(\"!!!\"): \"" + concated + "\"");
        System.out.println("New string hashcode: " + System.identityHashCode(concated));
        System.out.println("Original unchanged: \"" + original + "\"");
        
        String str1 = "Java";
        String str2 = str1;
        System.out.println("\nstr1 = \"" + str1 + "\", str2 = \"" + str2 + "\"");
        System.out.println("Same object? " + (str1 == str2));
        
        str1 = str1.toUpperCase();
        System.out.println("After str1 = str1.toUpperCase():");
        System.out.println("str1 = \"" + str1 + "\", str2 = \"" + str2 + "\"");
        System.out.println("Same object? " + (str1 == str2));
    }
}