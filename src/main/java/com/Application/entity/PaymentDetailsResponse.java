package com.Application.entity;

public class PaymentDetailsResponse {
    private String status;
    private String message;
    private PaymentTransaction paymentTransaction;

    public PaymentDetailsResponse() {
    }

    public PaymentDetailsResponse(String status, String message, PaymentTransaction paymentTransaction) {
        this.status = status;
        this.message = message;
        this.paymentTransaction = paymentTransaction;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PaymentTransaction getPaymentTransaction() {
        return paymentTransaction;
    }

    public void setPaymentTransaction(PaymentTransaction paymentTransaction) {
        this.paymentTransaction = paymentTransaction;
    }
}
