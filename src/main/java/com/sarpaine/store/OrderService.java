package com.sarpaine.store;

import org.springframework.stereotype.Service;
import com.sarpaine.store.dto.OrderRequest;
import com.sarpaine.store.dto.OrderResponse;
import java.util.UUID;

@Service
public class OrderService {
  private PaymentService paymentService;

  
  public OrderService(PaymentService paymentService) {
    this.paymentService = paymentService;
  }

  public OrderResponse placeOrder(OrderRequest orderRequest) {
    try {
      // Generate a unique order ID
      String orderId = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
      
      // Process the payment
      PaymentResult paymentResult = paymentService.processPayment(orderRequest.getAmount());
      
      if (paymentResult.isSuccessful()) {
        // Return success response
        return new OrderResponse(
          orderId, 
          "SUCCESS", 
          orderRequest.getAmount(), 
          "Order placed successfully. Transaction ID: " + paymentResult.getTransactionId()
        );
      } else {
        // Return payment failure response
        return new OrderResponse(
          null, 
          "PAYMENT_FAILED", 
          orderRequest.getAmount(), 
          "Payment failed: " + paymentResult.getMessage()
        );
      }
      
    } catch (Exception e) {
      // Return error response
      return new OrderResponse(
        null, 
        "FAILED", 
        orderRequest.getAmount(), 
        "Order failed: " + e.getMessage()
      );
    }
  }

  public void setPaymentService(PaymentService paymentService) {
    this.paymentService = paymentService;
  }
}