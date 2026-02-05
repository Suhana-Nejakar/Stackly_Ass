package com.company.payments;

public abstract class AbstractPayment {

    private final String transactionId;
    private final String customerName;

    public AbstractPayment(String transactionId, String customerName) {
        this.transactionId = transactionId;
        this.customerName = customerName;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getCustomerName() {
        return customerName;
    }

    // Hook method (each payment type implements its own security behavior)
    protected abstract boolean performSecurityChecks();

    // Reusable concrete method
    public void printReceipt(double amount) {
        System.out.println("----- RECEIPT -----");
        System.out.println("Customer: " + customerName);
        System.out.println("Transaction ID: " + transactionId);
        System.out.println("Amount: " + amount);
        System.out.println("-------------------");
    }

    // Optional template method (shows abstraction + reuse)
    public final void executePayment(PaymentMethod method, double amount) {
        method.logPayment(amount);

        if (!method.validate(amount)) {
            System.out.println("Validation failed. Payment not processed.");
            return;
        }

        boolean securityOk = performSecurityChecks();
        if (!securityOk) {
            System.out.println("Security checks failed. Payment declined.");
            return;
        }

        method.process(amount);
        printReceipt(amount);
    }
}