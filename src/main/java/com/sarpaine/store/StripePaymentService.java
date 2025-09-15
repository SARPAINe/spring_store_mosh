package com.sarpaine.store;

import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class StripePaymentService implements PaymentService {
  
  private final Random random = new Random();
  
  @Override
  public PaymentResult processPayment(double amount) {
    // Validate payment amount
    if (amount <= 0) {
      return new PaymentResult(
        null, 
        false, 
        amount, 
        "Payment amount must be greater than 0"
      );
    }
    
    if (amount > 10000) {
      return new PaymentResult(
        null, 
        false, 
        amount, 
        "Payment amount exceeds maximum limit of $10,000"
      );
    }
    
    // Simulate network delay
    try {
      Thread.sleep(100 + random.nextInt(200)); // 100-300ms delay
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      return new PaymentResult(
        null, 
        false, 
        amount, 
        "Payment processing interrupted"
      );
    }
    
    // Simulate payment failures (10% chance)
    if (random.nextInt(100) < 10) {
      return new PaymentResult(
        null, 
        false, 
        amount, 
        "Payment failed: Card declined by bank"
      );
    }
    
    // Generate mock transaction ID
    String transactionId = "txn_" + System.currentTimeMillis() + "_" + random.nextInt(1000);
    
    // Simulate different payment scenarios based on amount
    if (amount > 1000) {
      System.out.println("Processing high-value payment of $" + amount + " through Stripe with additional verification...");
      // Additional delay for high-value transactions
      try {
        Thread.sleep(200);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    } else {
      System.out.println("Processing payment of $" + amount + " through Stripe...");
    }
    
    System.out.println("Payment successful! Transaction ID: " + transactionId);
    
    return new PaymentResult(
      transactionId, 
      true, 
      amount, 
      "Payment processed successfully via Stripe"
    );
  }
}
