package com.Application.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "payment_transaction")
public class PaymentTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "loan_id")
    private Long loanId;

    @Column(name = "loan_amount")
    private double loanAmount;

    @Column(name = "penalty_amount")
    private double penaltyAmount;

    @Column(name = "total_amount")
    private double totalAmount;

    @Column(name = "repayment_date")
    private LocalDate repaymentDate;

    @Column(name = "paid_on_date")
    private LocalDate paidOnDate;

    @Column(name = "payment_status")
    private String paymentStatus;

}
