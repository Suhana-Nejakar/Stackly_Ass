package com.company.payments;

public class UpiPayment extends AbstractPayment implements PaymentMethod {

    private final String upiId;
    private final String provider; // GPAY, PHONEPE etc.

    public UpiPayment(String transactionId, String customerName, String upiId, String provider) {
        super(transactionId, customerName);
        this.upiId = upiId;
        this.provider = provider;
    }

    public String getUpiId() {
        return upiId;
    }

    public String getProvider() {
        return provider;
    }

    @Override
    public boolean validate(double amount) {
        if (amount <= 0) return false;
        return upiId != null && !upiId.trim().isEmpty();
    }

    @Override
    public void process(double amount) {
        System.out.println("Trying " + amount + " via UPI for " + getCustomerName());
        System.out.println("Payment successful via UPI (" + provider + "). Transaction ID: " + getTransactionId());
    }

    @Override
    public String getPaymentType() {
        return "UPI";
    }

    @Override
    protected boolean performSecurityChecks() {
        System.out.println("Security checks passed for transaction " + getTransactionId() + " (UPI PIN simulated)");
        return true;
    }
}