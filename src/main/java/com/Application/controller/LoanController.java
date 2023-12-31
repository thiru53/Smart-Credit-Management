package com.Application.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Autowired
    private LoanService loanService;

    @PostMapping("/process")
    public ResponseEntity<Map<String, String>> applyLoan(@RequestBody LoanApplicationRequest request) {

        Map<String, String> response = new HashMap<>();
        try{
            boolean isLoanApproved = loanService.applyLoan(request);
            response.put("status", "success");
            response.put("message", "Logic for Loan application approved and transferred");
            return ResponseEntity.ok(response);
        } catch (Exception e){
            response.put("status", "fail");
            response.put("message", e.getMessage());
        }
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);

    }
    @GetMapping
    public ResponseEntity<Object> viewAllLoans() {

        Map<String, String> response = new HashMap<>();
        try{
            List<Loan> loan = loanService.findAll();
            return ResponseEntity.ok(loan);
        } catch (Exception e) {
            response.put("status", "failure");
            response.put("message", e.getMessage());
        }
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/{loanId}")
    public ResponseEntity<Object> viewLoanById(@PathVariable("loanId") Long loanId) {

        Map<String, String> response = new HashMap<>();
        try{
           Loan loan = loanService.findById(loanId);
            return ResponseEntity.ok(loan);
        } catch (Exception e) {
            response.put("status", "failure");
            response.put("message", e.getMessage());
        }
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);

    }
    @GetMapping("/borrowers/{borrowerId}")
    public ResponseEntity<Object> viewLoansByBorrowerId(@PathVariable("borrowerId") String borrowerId) {

        Map<String, String> response = new HashMap<>();
        try{
            List<Loan> loans = loanService.findByBorrowerId(borrowerId);
            if(loans.isEmpty()){
                response.put("status", "failure");
                response.put("message", "No loan data found for borrower ID: "+borrowerId);
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.ok(loans);
        } catch (Exception e) {
            response.put("status", "failure");
            response.put("message", e.getMessage());
        }
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/creditlimit/{borrowerId}")
    public ResponseEntity<Object> viewCreditLimitByBorrowerId(@PathVariable("borrowerId") String borrowerId) {

        Map<String, String> response = new HashMap<>();
        try{
            CreditLimit creditLimit = loanService.getCreditLimitByBorrowerId(borrowerId);
            if(Objects.isNull(creditLimit)){
                response.put("status", "failure");
                response.put("message", "No credit limit data found for borrower ID: "+borrowerId);
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.ok(creditLimit);
        }catch (Exception e){
            response.put("status", "failure");
            response.put("message", e.getMessage());
        }
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);

    }



    @GetMapping("/paymentdetails/{loanId}")
    public ResponseEntity<PaymentDetailsResponse> getPaymentDetails(@PathVariable("loanId") Long loanId) {


        PaymentDetailsResponse response = new PaymentDetailsResponse();
        try{
            PaymentTransaction paymentTransaction = loanService.getPaymentDetailsByLoanId(loanId);
            if(Objects.isNull(paymentTransaction)) {
                response.setStatus("failure");
                response.setMessage("invalid loan ID.");
            } else {
                response.setStatus("success");
                response.setMessage("Payment transaction details retrieved successfully");
            }
            response.setPaymentTransaction(paymentTransaction);
            return ResponseEntity.ok(response);
        }catch (Exception e){
            response.setStatus("failure");
            response.setMessage(e.getMessage());
        }
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);

    }

    @PostMapping("/paymentdetails/{loanId}/repayment")
    public ResponseEntity<Object> repayLoan(@PathVariable("loanId") Long loanId, @RequestBody RepaymentRequest request) {

        Map<String, String> response = new HashMap<>();
        try{
            PaymentTransaction paymentTransaction = loanService.getPaymentDetailsByLoanId(loanId);

            if(Objects.isNull(paymentTransaction)){
                response.put("status", "failure");
                response.put("message", "No PaymentTransaction found for loanId: "+loanId);
                return ResponseEntity.ok(response);
            }
            if(request.getRepaymentAmount() == paymentTransaction.getTotalAmount()){
                paymentTransaction.setPaymentStatus("Paid");
                paymentTransaction.setRepaymentDate(LocalDate.now());
                loanService.savePaymentTransaction(paymentTransaction);
                response.put("status", "success");
                response.put("message", "Loan repayment successful.");
                return ResponseEntity.ok(response);
            } else {
                response.put("status", "failure");
                response.put("message", "Payment cannot be done as you need to pay the full loan amount.");
                return ResponseEntity.ok(response);
            }
        }catch (Exception e){
            response.put("status", "failure");
            response.put("message", e.getMessage());
        }
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);

    }
}
