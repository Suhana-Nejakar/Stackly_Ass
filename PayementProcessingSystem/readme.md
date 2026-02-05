
# Payment Processing System – Abstraction & Interfaces (Java)

This document contains **all source code** for the Payment Processing System assignment.

---

## Package Structure

```
com.company.payments
├── PaymentMethod.java
├── AbstractPayment.java
├── CardPayment.java
├── UpiPayment.java
├── WalletPayment.java
├── PaymentService.java
└── PaymentApp.java
```

---

## PaymentMethod.java
```java
package com.company.payments;

public interface PaymentMethod {

    boolean validate(double amount);

    void process(double amount);

    String getPaymentType();

    default void logPayment(double amount) {
        System.out.println("Payment of " + amount +
                " processed via " + getPaymentType());
    }
}
```
---

## AbstractPayment.java
```java
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

    protected abstract boolean performSecurityChecks();

    public void printReceipt(double amount) {
        System.out.println("----- RECEIPT -----");
        System.out.println("Customer: " + customerName);
        System.out.println("Transaction ID: " + transactionId);
        System.out.println("Amount: " + amount);
        System.out.println("-------------------");
    }

    public final void executePayment(PaymentMethod method, double amount) {
        method.logPayment(amount);

        if (!method.validate(amount)) {
            System.out.println("Validation failed. Payment not processed.");
            return;
        }

        if (!performSecurityChecks()) {
            System.out.println("Security checks failed.");
            return;
        }

        method.process(amount);
        printReceipt(amount);
    }
}
```
---

## CardPayment.java
```java
package com.company.payments;

public class CardPayment extends AbstractPayment implements PaymentMethod {

    private final String cardNumber;
    private final String cardType;

    public CardPayment(String transactionId, String customerName,
                       String cardNumber, String cardType) {
        super(transactionId, customerName);
        this.cardNumber = cardNumber;
        this.cardType = cardType;
    }

    public String getMaskedCardNumber() {
        String last4 = cardNumber.substring(cardNumber.length() - 4);
        return "**** **** **** " + last4;
    }

    @Override
    public boolean validate(double amount) {
        return amount > 0 && cardNumber != null && !cardNumber.isEmpty();
    }

    @Override
    public void process(double amount) {
        System.out.println("Processing payment of " + amount +
                " via CARD for " + getCustomerName());
        System.out.println("Payment successful. Transaction ID: " +
                getTransactionId());
    }

    @Override
    public String getPaymentType() {
        return "CARD";
    }

    @Override
    protected boolean performSecurityChecks() {
        System.out.println("Security checks passed for transaction " +
                getTransactionId());
        return true;
    }
}
```
---

## UpiPayment.java
```java
package com.company.payments;

public class UpiPayment extends AbstractPayment implements PaymentMethod {

    private final String upiId;
    private final String provider;

    public UpiPayment(String transactionId, String customerName,
                      String upiId, String provider) {
        super(transactionId, customerName);
        this.upiId = upiId;
        this.provider = provider;
    }

    public String getUpiId() {
        return upiId;
    }

    @Override
    public boolean validate(double amount) {
        return amount > 0 && upiId != null && !upiId.isEmpty();
    }

    @Override
    public void process(double amount) {
        System.out.println("Payment successful via UPI (" +
                provider + ")");
    }

    @Override
    public String getPaymentType() {
        return "UPI";
    }

    @Override
    protected boolean performSecurityChecks() {
        System.out.println("UPI PIN verified for transaction " +
                getTransactionId());
        return true;
    }
}
```
---

## WalletPayment.java
```java
package com.company.payments;

public class WalletPayment extends AbstractPayment implements PaymentMethod {

    private double walletBalance;
    private final String walletName;

    public WalletPayment(String transactionId, String customerName,
                         double walletBalance, String walletName) {
        super(transactionId, customerName);
        this.walletBalance = walletBalance;
        this.walletName = walletName;
    }

    @Override
    public boolean validate(double amount) {
        return amount > 0 && walletBalance >= amount;
    }

    @Override
    public void process(double amount) {
        walletBalance -= amount;
        System.out.println("Wallet payment successful. Remaining balance: " +
                walletBalance);
    }

    @Override
    public String getPaymentType() {
        return "WALLET";
    }

    @Override
    protected boolean performSecurityChecks() {
        System.out.println("Wallet PIN verified for transaction " +
                getTransactionId());
        return true;
    }
}
```
---

## PaymentService.java
```java
package com.company.payments;

import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class PaymentService {

    private final List<PaymentMethod> paymentMethods;

    public PaymentService(List<PaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public void registerPaymentMethod(PaymentMethod method) {
        paymentMethods.add(method);
    }

    public void processPayment(String customerName, double amount,
                               PaymentMethod method) {

        if (!method.validate(amount)) {
            System.out.println("Validation failed.");
            return;
        }

        if (method instanceof AbstractPayment) {
            AbstractPayment ap = (AbstractPayment) method;
            ap.executePayment(method, amount);
        }
    }

    public Set<String> getSupportedPaymentTypes() {
        Set<String> types = new HashSet<>();
        for (PaymentMethod pm : paymentMethods) {
            types.add(pm.getPaymentType());
        }
        return types;
    }
}
```
---

## PaymentApp.java
```java
package com.company.payments;

import java.util.ArrayList;
import java.util.List;

public class PaymentApp {

    public static void main(String[] args) {

        List<PaymentMethod> methods = new ArrayList<>();
        PaymentService service = new PaymentService(methods);

        PaymentMethod card =
                new CardPayment("TXN1", "Rahul",
                        "1234567812341234", "VISA");

        PaymentMethod upi =
                new UpiPayment("TXN2", "Anjali",
                        "anjali@upi", "GPAY");

        PaymentMethod wallet =
                new WalletPayment("TXN3", "Vikram",
                        3000.0, "MyWallet");

        service.registerPaymentMethod(card);
        service.registerPaymentMethod(upi);
        service.registerPaymentMethod(wallet);

        service.processPayment("Rahul", 1500, card);

        System.out.println(service.getSupportedPaymentTypes());
    }
}
```
---

✅ End of file
