package com.sarpaine.store;

import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class PayPalPaymentService implements PaymentService {
  
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
    
    if (amount > 5000) {
      return new PaymentResult(
        null, 
        false, 
        amount, 
        "PayPal payment amount exceeds maximum limit of $5,000"
      );
    }
    
    // Simulate network delay (PayPal is typically slower than Stripe)
    try {
      Thread.sleep(200 + random.nextInt(300)); // 200-500ms delay
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      return new PaymentResult(
        null, 
        false, 
        amount, 
        "PayPal payment processing interrupted"
      );
    }
    
    // Simulate payment failures (15% chance - higher than Stripe)
    if (random.nextInt(100) < 15) {
      return new PaymentResult(
        null, 
        false, 
        amount, 
        "PayPal payment failed: Insufficient funds in PayPal account"
      );
    }
    
    // Generate mock PayPal transaction ID
    String transactionId = "PP_" + System.currentTimeMillis() + "_" + random.nextInt(10000);
    
    // PayPal-specific processing logic
    if (amount > 500) {
      System.out.println("Processing high-value PayPal payment of $" + amount + " with buyer protection...");
      // Additional delay for PayPal buyer protection
      try {
        Thread.sleep(150);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    } else {
      System.out.println("Processing PayPal payment of $" + amount + "...");
    }
    
    System.out.println("PayPal payment successful! Transaction ID: " + transactionId);
    
    return new PaymentResult(
      transactionId, 
      true, 
      amount, 
      "Payment processed successfully via PayPal"
    );
  }
}
