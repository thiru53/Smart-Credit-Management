package com.Application.entity;

import lombok.Data;

@Data
public class LoanApplicationRequest {
    private String borrowerId;
    private double loanAmount;

}


