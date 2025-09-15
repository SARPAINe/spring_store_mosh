package com.sarpaine.store;

public interface PaymentService {
  PaymentResult processPayment(double amount);
}