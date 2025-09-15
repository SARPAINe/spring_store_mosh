package com.sarpaine.store;

import org.springframework.stereotype.Service;
import com.sarpaine.store.dto.OrderRequest;
import com.sarpaine.store.dto.OrderResponse;
import java.util.Map;
import java.util.UUID;

@Service
public class OrderService {
  private final Map<String, PaymentService> paymentServices;
  private final PaymentService defaultPaymentService;

  public OrderService(Map<String, PaymentService> paymentServices) {
    this.paymentServices = paymentServices;
    // Set Stripe as default (fallback)
    this.defaultPaymentService = paymentServices.get("stripePaymentService");
    
    if (defaultPaymentService == null) {
      throw new IllegalStateException("Default payment service (Stripe) not found");
    }
  }

  public OrderResponse placeOrder(OrderRequest orderRequest) {
    try {
      // Generate a unique order ID
      String orderId = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
      
      // Choose payment service dynamically
      PaymentService paymentService = getPaymentService(orderRequest.getPaymentMethod());
      String methodName = getPaymentMethodName(orderRequest.getPaymentMethod());
      
      // Process the payment
      PaymentResult paymentResult = paymentService.processPayment(orderRequest.getAmount());
      
      if (paymentResult.isSuccessful()) {
        return new OrderResponse(
          orderId, 
          "SUCCESS", 
          orderRequest.getAmount(), 
          "Order placed successfully via " + methodName + 
          ". Transaction ID: " + paymentResult.getTransactionId()
        );
      } else {
        return new OrderResponse(
          null, 
          "PAYMENT_FAILED", 
          orderRequest.getAmount(), 
          "Payment failed via " + methodName + ": " + paymentResult.getMessage()
        );
      }
      
    } catch (Exception e) {
      return new OrderResponse(
        null, 
        "FAILED", 
        orderRequest.getAmount(), 
        "Order failed: " + e.getMessage()
      );
    }
  }

  private PaymentService getPaymentService(String paymentMethod) {
    if (paymentMethod == null || paymentMethod.trim().isEmpty()) {
      return defaultPaymentService;
    }
    
    String serviceName = paymentMethod.toLowerCase() + "PaymentService";
    return paymentServices.getOrDefault(serviceName, defaultPaymentService);
  }
  
  private String getPaymentMethodName(String paymentMethod) {
    if ("paypal".equalsIgnoreCase(paymentMethod)) {
      return "PayPal";
    }
    return "Stripe"; // Default
  }

  // For backward compatibility
  public void setPaymentService(PaymentService paymentService) {
    // Deprecated - kept for compatibility
  }
}