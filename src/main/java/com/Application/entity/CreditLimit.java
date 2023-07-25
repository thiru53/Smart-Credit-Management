package com.Application.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "credit_limit")
public class CreditLimit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "borrower_id")
    private String borrowerId;

    @Column(name = "credit_limit")
    private double creditLimit;

    @Column(name = "used_amount")
    private double usedAmount;

    @Column
    private double remainingAmount;

}
