package com.sarpaine.store;

import java.time.LocalDateTime;

public class PaymentResult {
    private final String transactionId;
    private final boolean successful;
    private final double amount;
    private final String message;
    private final LocalDateTime timestamp;
    
    public PaymentResult(String transactionId, boolean successful, double amount, String message) {
        this.transactionId = transactionId;
        this.successful = successful;
        this.amount = amount;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
    
    // Getters
    public String getTransactionId() {
        return transactionId;
    }
    
    public boolean isSuccessful() {
        return successful;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public String getMessage() {
        return message;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
