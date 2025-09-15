package com.sarpaine.store.dto;

import java.time.LocalDateTime;

public class OrderResponse {
    private String orderId;
    private String status;
    private Double amount;
    private String message;
    private LocalDateTime timestamp;

    // Default constructor
    public OrderResponse() {
        this.timestamp = LocalDateTime.now();
    }

    // Constructor with parameters
    public OrderResponse(String orderId, String status, Double amount, String message) {
        this.orderId = orderId;
        this.status = status;
        this.amount = amount;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    // Getters and setters
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
