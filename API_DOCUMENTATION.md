# Store API Documentation

## Overview

This is a RESTful API for placing orders in the store application. The API includes validation, error handling, and follows Spring Boot best practices.

## Base URL

```
http://localhost:8080/api
```

## Endpoints

### 1. Health Check

**GET** `/orders/health`

Check if the order service is running.

**Response:**

```
Order service is running
```

### 2. Place Order

**POST** `/orders`

Place a new order with payment processing using your preferred payment method.

**Request Body:**

```json
{
  "amount": 50.0,
  "customerEmail": "customer@example.com",
  "productName": "Test Product",
  "paymentMethod": "stripe"
}
```

**Request Validation:**

- `amount`: Required, must be greater than 0
- `customerEmail`: Optional
- `productName`: Optional
- `paymentMethod`: Optional, accepts "stripe" or "paypal" (defaults to "stripe")

**Payment Method Differences:**

- **Stripe**: Maximum limit $10,000, faster processing, lower failure rate (10%)
- **PayPal**: Maximum limit $5,000, slower processing, higher failure rate (15%), includes buyer protection

**Success Response (201 Created):**

```json
{
  "orderId": "FB812238",
  "status": "SUCCESS",
  "amount": 50.0,
  "message": "Order placed successfully and payment processed",
  "timestamp": "2025-09-15T19:37:30.123456"
}
```

**Error Response (400 Bad Request) - Validation:**

```json
{
  "timestamp": "2025-09-15T19:37:30.123456",
  "status": 400,
  "error": "Validation Failed",
  "message": "Invalid input data",
  "fieldErrors": {
    "amount": "Amount is required"
  }
}
```

**Error Response (400 Bad Request) - Processing Failed:**

```json
{
  "orderId": null,
  "status": "FAILED",
  "amount": 50.0,
  "message": "Order failed: Payment processing error",
  "timestamp": "2025-09-15T19:37:30.123456"
}
```

## Example Usage

### Using curl

1. **Health Check:**

```bash
curl -X GET http://localhost:8080/api/orders/health
```

2. **Place Order with Stripe (default):**

```bash
curl -X POST http://localhost:8080/api/orders \
  -H "Content-Type: application/json" \
  -d '{
    "amount": 50.0,
    "customerEmail": "customer@example.com",
    "productName": "Test Product",
    "paymentMethod": "stripe"
  }'
```

3. **Place Order with PayPal:**

```bash
curl -X POST http://localhost:8080/api/orders \
  -H "Content-Type: application/json" \
  -d '{
    "amount": 150.0,
    "customerEmail": "paypal-user@example.com",
    "productName": "PayPal Product",
    "paymentMethod": "paypal"
  }'
```

4. **Test Validation:**

```bash
curl -X POST http://localhost:8080/api/orders \
  -H "Content-Type: application/json" \
  -d '{
    "amount": -10.0
  }'
```

## Architecture

The API follows these Spring Boot best practices:

1. **Separation of Concerns:**

   - `OrderController`: Handles HTTP requests/responses
   - `OrderService`: Contains business logic
   - `PaymentService`: Handles payment processing
   - DTOs: Data transfer objects for request/response

2. **Validation:**

   - Uses Bean Validation annotations (`@NotNull`, `@Positive`)
   - Global exception handler for consistent error responses

3. **Error Handling:**

   - `GlobalExceptionHandler` provides consistent error responses
   - Proper HTTP status codes
   - Detailed validation error messages

4. **Dependency Injection:**

   - Constructor-based dependency injection
   - Proper use of Spring annotations (`@RestController`, `@Service`)

5. **Response Structure:**
   - Consistent response format
   - Meaningful HTTP status codes
   - Timestamps for tracking

## Technologies Used

- Spring Boot 3.5.5
- Spring Web (REST API)
- Spring Validation
- Jackson (JSON processing)
- Maven (build tool)
