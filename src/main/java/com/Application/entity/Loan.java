package com.Application.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "loan")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "borrower_id")
    private String borrowerId;

    @Column(name = "loan_amount")
    private double loanAmount;

    @Column(name = "loan_date")
    private LocalDate loanDate;

    @Column(name = "repayment_date")
    private LocalDate repaymentDate;

    @Column(name = "payment_status")
    private String paymentStatus;

    @Column(name = "paid_date")
    private LocalDate paidDate;


}