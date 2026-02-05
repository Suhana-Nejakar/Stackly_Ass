package com.company.payments;

public class CardPayment extends AbstractPayment implements PaymentMethod {

    private final String cardNumber;
    private final String cardType; // VISA, MASTERCARD etc.

    public CardPayment(String transactionId, String customerName, String cardNumber, String cardType) {
        super(transactionId, customerName);
        this.cardNumber = cardNumber;
        this.cardType = cardType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardType() {
        return cardType;
    }

    public String getMaskedCardNumber() {
        if (cardNumber == null || cardNumber.trim().isEmpty()) return "**** **** **** ****";
        String digits = cardNumber.trim();
        String last4 = digits.length() >= 4 ? digits.substring(digits.length() - 4) : digits;
        return "**** **** **** " + last4;
    }

    @Override
    public boolean validate(double amount) {
        if (amount <= 0) return false;
        return cardNumber != null && !cardNumber.trim().isEmpty();
    }

    @Override
    public void process(double amount) {
        System.out.println("Processing payment of " + amount + " via CARD for " + getCustomerName());
        System.out.println("Payment successful. Transaction ID: " + getTransactionId() + " (" + cardType + ")");
    }

    @Override
    public String getPaymentType() {
        return "CARD";
    }

    @Override
    protected boolean performSecurityChecks() {
        System.out.println("Security checks passed for transaction " + getTransactionId() + " (CVV/OTP simulated)");
        return true;
    }
}