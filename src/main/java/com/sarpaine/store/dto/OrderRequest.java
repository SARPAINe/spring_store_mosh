package com.sarpaine.store.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class OrderRequest {
    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be greater than 0")
    private Double amount;
    
    private String customerEmail;
    private String productName;
    private String paymentMethod; // "stripe" or "paypal", defaults to "stripe"

    // Default constructor
    public OrderRequest() {}

    // Constructor with parameters
    public OrderRequest(Double amount, String customerEmail, String productName) {
        this.amount = amount;
        this.customerEmail = customerEmail;
        this.productName = productName;
        this.paymentMethod = "stripe"; // default
    }

    // Constructor with payment method
    public OrderRequest(Double amount, String customerEmail, String productName, String paymentMethod) {
        this.amount = amount;
        this.customerEmail = customerEmail;
        this.productName = productName;
        this.paymentMethod = paymentMethod;
    }

    // Getters and setters
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
