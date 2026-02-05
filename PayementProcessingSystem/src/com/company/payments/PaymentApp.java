package com.company.payments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class PaymentApp {

    public static void main(String[] args) {

        // ✅ Initialize list in App and inject to service
        List<PaymentMethod> methods = new ArrayList<>();
        PaymentService service = new PaymentService(methods);

        // ✅ Create at least 6 payment objects (2 each)
        PaymentMethod card1 = new CardPayment("TXN123", "Rahul", "1234567812341234", "VISA");
        PaymentMethod card2 = new CardPayment("TXN124", "Anjali", "9999888877776666", "MASTERCARD");

        PaymentMethod upi1 = new UpiPayment("TXN456", "Anjali", "anjali@upi", "GPAY");
        PaymentMethod upi2 = new UpiPayment("TXN457", "Vikram", "vikram@upi", "PHONEPE");

        PaymentMethod wallet1 = new WalletPayment("TXN789", "Vikram", 3000.0, "MyWallet");
        PaymentMethod wallet2 = new WalletPayment("TXN790", "Rahul", 800.0, "FastPayWallet");

        // ✅ Register all
        service.registerPaymentMethod(card1);
        service.registerPaymentMethod(card2);
        service.registerPaymentMethod(upi1);
        service.registerPaymentMethod(upi2);
        service.registerPaymentMethod(wallet1);
        service.registerPaymentMethod(wallet2);

        // 3) Display all registered methods
        service.displayAllPaymentMethods();

        // 2) Process single payment
        System.out.println("\n===== PROCESS SINGLE PAYMENT =====");
        service.processPayment("Rahul", 1500.0, card1);

        // 4) Process batch payments (UPI)
        System.out.println();
        List<Double> batchAmounts = Arrays.asList(500.0, 1200.0, -10.0);
        service.processBatch(batchAmounts, upi1);

        // Wallet batch to show balance effect + validation failure when insufficient
        System.out.println();
        service.processBatch(Arrays.asList(500.0, 2000.0, 700.0), wallet1);

        // 5) Supported payment types
        System.out.println("\n===== SUPPORTED PAYMENT TYPES =====");
        Set<String> types = service.getSupportedPaymentTypes();
        System.out.println(types);
    }
}