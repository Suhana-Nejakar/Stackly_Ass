package com.company.payments;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PaymentService {

    private final List<PaymentMethod> paymentMethods;

    // ✅ Inject list via constructor (no new ArrayList inside here)
    public PaymentService(List<PaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    // 1. Register payment methods
    public void registerPaymentMethod(PaymentMethod method) {
        paymentMethods.add(method);
    }

    // 2. Process a single payment
    public void processPayment(String customerName, double amount, PaymentMethod method) {
        System.out.println("Processing payment of " + amount + " via " + method.getPaymentType() + " for " + customerName);

        if (!method.validate(amount)) {
            System.out.println("Validation failed. Payment not processed.");
            return;
        }

        // If method is also AbstractPayment, run its security + receipt in a clean way
        if (method instanceof AbstractPayment) {
            AbstractPayment ap = (AbstractPayment) method;

            // optional: check that caller name matches object name (kept simple)
            boolean securityOk = ap.performSecurityChecks();
            if (!securityOk) {
                System.out.println("Security checks failed. Payment declined.");
                return;
            }

            method.process(amount);
            ap.printReceipt(amount);
        } else {
            // fallback (not expected in this assignment)
            method.process(amount);
        }
    }

    // 3. Display all registered payment methods
    public void displayAllPaymentMethods() {
        System.out.println("===== REGISTERED PAYMENT METHODS =====");
        for (PaymentMethod pm : paymentMethods) {
            System.out.println(buildDisplayLine(pm));
        }
    }

    private String buildDisplayLine(PaymentMethod pm) {
        if (pm instanceof CardPayment) {
            CardPayment c = (CardPayment) pm;
            return "CARD - " + c.getMaskedCardNumber() + " (" + c.getCustomerName() + ")";
        }
        if (pm instanceof UpiPayment) {
            UpiPayment u = (UpiPayment) pm;
            return "UPI - " + u.getUpiId() + " (" + u.getCustomerName() + ")";
        }
        if (pm instanceof WalletPayment) {
            WalletPayment w = (WalletPayment) pm;
            return "WALLET - " + w.getWalletName() + " (" + w.getCustomerName() + ")";
        }
        return pm.getPaymentType();
    }

    // 4. Process a batch of payments
    public void processBatch(List<Double> amounts, PaymentMethod method) {
        System.out.println("===== PROCESS BATCH PAYMENTS (" + method.getPaymentType() + ") =====");
        for (Double amt : amounts) {
            System.out.println("Trying " + amt + " via " + method.getPaymentType() + " for "
                    + (method instanceof AbstractPayment ? ((AbstractPayment) method).getCustomerName() : "Customer"));

            if (!method.validate(amt)) {
                System.out.println("Validation failed for amount " + amt + ". Skipping.");
                continue;
            }

            if (method instanceof AbstractPayment) {
                AbstractPayment ap = (AbstractPayment) method;
                if (!ap.performSecurityChecks()) {
                    System.out.println("Security failed for amount " + amt + ". Skipping.");
                    continue;
                }
                method.process(amt);
                ap.printReceipt(amt);
            } else {
                method.process(amt);
            }
        }
    }

    // 5. Find supported payment types
    public Set<String> getSupportedPaymentTypes() {
        Set<String> types = new HashSet<>();
        for (PaymentMethod pm : paymentMethods) {
            types.add(pm.getPaymentType());
        }
        return types;
    }

    // Helper (optional) - not required, but useful if you want a copy
    public List<PaymentMethod> getRegisteredMethodsSnapshot() {
        return new ArrayList<>(paymentMethods);
    }
}