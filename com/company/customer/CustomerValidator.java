package com.company.customer;

public class CustomerValidator {
    public static boolean isValidEmail(String email) {
        if (!email.contains("@")) {
            return false;
        }
        
        if (!email.endsWith(".com")) {
            return false;
        }
        
        int atIndex = email.indexOf("@");
        int dotComIndex = email.indexOf(".com");
        
        return atIndex > 0 && dotComIndex > atIndex;
    }
    
    public static boolean isValidPhone(String phone) {
        if (phone.length() != 10) {
            return false;
        }
        
        if (!phone.startsWith("9") && !phone.startsWith("8") && 
            !phone.startsWith("7") && !phone.startsWith("6")) {
            return false;
        }
        
        String areaCode = phone.substring(0, 3);
        
        return !areaCode.equals("000");
    }
    
    public static boolean isCountryAllowed(String country) {
        String trimmedCountry = country.trim();
        
        return trimmedCountry.equalsIgnoreCase("India") ||
               trimmedCountry.equalsIgnoreCase("USA") ||
               trimmedCountry.equalsIgnoreCase("Canada");
    }
    
    public static void analyzeName(String name) {
        System.out.println("\n=== Name Analysis for: " + name + " ===");
        
        String lowerName = name.toLowerCase();
        System.out.println("Lowercase: " + lowerName);
        
        String hyphenated = name.replace(' ', '-');
        System.out.println("Hyphenated: " + hyphenated);
        
        String[] nameParts = name.split(" ");
        System.out.println("Name parts (" + nameParts.length + "):");
        for (int i = 0; i < nameParts.length; i++) {
            System.out.println("  Part " + (i + 1) + ": " + nameParts[i]);
        }
        
        System.out.println("Original name length: " + name.length());
        
        if (name.length() > 0) {
            System.out.println("First character: " + name.charAt(0));
            System.out.println("Last character: " + name.charAt(name.length() - 1));
        }
        
        String vowels = "aeiouAEIOU";
        int vowelCount = 0;
        for (char c : name.toCharArray()) {
            if (vowels.indexOf(c) != -1) {
                vowelCount++;
            }
        }
        System.out.println("Vowel count: " + vowelCount);
    }
}