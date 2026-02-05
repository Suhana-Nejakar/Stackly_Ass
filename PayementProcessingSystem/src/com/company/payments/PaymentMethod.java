package com.company.payments;

public interface PaymentMethod {

    boolean validate(double amount);

    void process(double amount);

    String getPaymentType();

    // Optional interface-level abstraction
    default void logPayment(double amount) {
        System.out.println("Payment of " + amount + " processed via " + getPaymentType());
    }
}