package com.Application.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Application.Repository.CreditLimitRepository;
import com.Application.Repository.LoanRepository;
import com.Application.Repository.PaymentTransactionRepository;
import com.Application.Service.LoanService;
import com.Application.entity.CreditLimit;
import com.Application.entity.Loan;
import com.Application.entity.LoanApplicationRequest;
import com.Application.entity.PaymentDetailsResponse;
import com.Application.entity.PaymentTransaction;
import com.Application.entity.RepaymentRequest;

@RestController
@RequestMapping("/loans")
public class LoanController {


    @PostMapping("/process")
    public ResponseEntity<Map<String, String>> applyLoan(@RequestBody LoanApplicationRequest request) {

        Map<String, String> response = new HashMap<>();
        response.put("status", "success/failure");
        response.put("message", "Logic for Loan application approved and transferred");
        return ResponseEntity.ok(response);

    }
    @GetMapping
    public ResponseEntity<Object> viewAllLoans() {

        Map<String, String> response = new HashMap<>();
        response.put("status", "success/failure");
        response.put("message", "logic for retriving  loan data ");
        return ResponseEntity.ok(response);



    }
    @GetMapping("/{loanId}")
    public ResponseEntity<Object> viewLoanById(@PathVariable("loanId") Long loanId) {

        Map<String, String> response = new HashMap<>();
        response.put("status", "succcess/failure");
        response.put("message","logic for retriving  loan data using loan id ");
        return ResponseEntity.ok(response);

    }
    @GetMapping("/borrowers/{borrowerId}")
    public ResponseEntity<Object> viewLoansByBorrowerId(@PathVariable("borrowerId") String borrowerId) {

        Map<String, String> response = new HashMap<>();
        response.put("status", "success/failure");
        response.put("message", "logic for retriving  loan data using borrower id ");
        return ResponseEntity.ok(response);



    }

    @GetMapping("/creditlimit/{borrowerId}")
    public ResponseEntity<Object> viewCreditLimitByBorrowerId(@PathVariable("borrowerId") String borrowerId) {

        Map<String, String> response = new HashMap<>();
        response.put("status", "success/failure");
        response.put("message", "logic for retriving  creditlimit  data using borrower id ");
        return ResponseEntity.ok(response);

    }



    @GetMapping("/paymentdetails/{loanId}")
    public ResponseEntity<PaymentDetailsResponse> getPaymentDetails(@PathVariable("loanId") Long loanId) {


        PaymentDetailsResponse response = new PaymentDetailsResponse();
        response.setStatus("success");
        response.setMessage("Logic for Payment transaction details retrieved successfully.");

        return ResponseEntity.ok(response);

    }

    @PostMapping("/paymentdetails/{loanId}/repayment")
    public ResponseEntity<Object> repayLoan(@PathVariable("loanId") Long loanId, @RequestBody RepaymentRequest request) {

        Map<String, String> response = new HashMap<>();
        response.put("status", "failure");
        response.put("message", "Logic for  for this loan.");
        return ResponseEntity.ok(response);

    }



}
