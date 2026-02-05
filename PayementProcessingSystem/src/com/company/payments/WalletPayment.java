package com.company.payments;

public class WalletPayment extends AbstractPayment implements PaymentMethod {

    private double walletBalance;
    private final String walletName;

    public WalletPayment(String transactionId, String customerName, double walletBalance, String walletName) {
        super(transactionId, customerName);
        this.walletBalance = walletBalance;
        this.walletName = walletName;
    }

    public double getWalletBalance() {
        return walletBalance;
    }

    public String getWalletName() {
        return walletName;
    }

    @Override
    public boolean validate(double amount) {
        return amount > 0 && walletBalance >= amount;
    }

    @Override
    public void process(double amount) {
        System.out.println("Processing " + amount + " via WALLET for " + getCustomerName() + " (" + walletName + ")");
        walletBalance -= amount;
        System.out.println("Payment successful. Remaining wallet balance: " + walletBalance);
    }

    @Override
    public String getPaymentType() {
        return "WALLET";
    }

    @Override
    protected boolean performSecurityChecks() {
        System.out.println("Security checks passed for transaction " + getTransactionId() + " (Wallet PIN simulated)");
        return true;
    }
}