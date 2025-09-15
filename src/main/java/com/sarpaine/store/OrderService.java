package com.sarpaine.store;

import org.springframework.stereotype.Service;

@Service
public class OrderService {
  private PaymentService paymentService;

  
  public OrderService(PaymentService paymentService) {
    this.paymentService = paymentService;
  }
  
  public void placeOrder(){
    paymentService.processPayment(100.0);
  }

  public void setPaymentService(PaymentService paymentService) {
    this.paymentService = paymentService;
  }
}