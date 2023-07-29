package com.Application.Service;

import com.Application.Repository.PaymentTransactionRepository;
import com.Application.entity.CreditLimit;
import com.Application.entity.Loan;
import com.Application.entity.PaymentTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Application.Repository.CreditLimitRepository;
import com.Application.Repository.LoanRepository;
import com.Application.entity.LoanApplicationRequest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class LoanService {
    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private CreditLimitRepository creditLimitRepository;

    @Autowired
    private PaymentTransactionRepository paymentTransactionRepository;

    @Transactional
    public boolean applyLoan(LoanApplicationRequest request){
        List<Loan> loans = loanRepository.findByBorrowerId(request.getBorrowerId());
        if(!loans.isEmpty()) {
            for (Loan l : loans) {
                if (l.getPaymentStatus().equalsIgnoreCase("Not Paid") &&
                        l.getRepaymentDate().isAfter(LocalDate.now())
                ) {
                    // prevent loan
                }
            }
        } else {
            Loan loan = populateNewLoanByRequest(request);
            loanRepository.save(loan);

            CreditLimit creditLimit = creditLimitRepository.findByBorrowerId(request.getBorrowerId());
            if (creditLimit.getCreditLimit() < request.getLoanAmount() || creditLimit.getRemainingAmount() < request.getLoanAmount()) {
                throw new RuntimeException("Loan amount exceeds the remaining credit limit.");
            } else {
                creditLimitRepository.save(populateNewCreditLimitByRequest(request));
            }
        }
        return true;
    }

    private Loan populateNewLoanByRequest(LoanApplicationRequest request){
        Loan loan = new Loan();
        loan.setBorrowerId(request.getBorrowerId());
        loan.setLoanAmount(5000);
        loan.setLoanDate(LocalDate.now());
        loan.setPaymentStatus("Not Paid");
        loan.setRepaymentDate(LocalDate.now().plusMonths(1));
        return loan;
    }
    private CreditLimit populateNewCreditLimitByRequest(LoanApplicationRequest request){
        CreditLimit  creditLimit = new CreditLimit();
        creditLimit.setBorrowerId(request.getBorrowerId());
        creditLimit.setUsedAmount(0);
        creditLimit.setRemainingAmount(5000);
        creditLimit.setCreditLimit(5000);
        return creditLimit;
    }


    public Loan findById(Long loanId) {
        return loanRepository.findById(loanId).orElseThrow(() -> new RuntimeException("Invalid load ID."));
    }

    public List<Loan> findAll() {
        return loanRepository.findAll();
    }

    public List<Loan> findByBorrowerId(String borrowerId) {
        return loanRepository.findByBorrowerId(borrowerId);
    }

    public CreditLimit getCreditLimitByBorrowerId(String borrowerId) {
        return creditLimitRepository.findByBorrowerId(borrowerId);
    }

    public PaymentTransaction getPaymentDetailsByLoanId(Long loanId) {
        return paymentTransactionRepository.findByLoanId(loanId);
    }

    public void savePaymentTransaction(PaymentTransaction paymentTransaction) {
        Loan loan = loanRepository.findById(paymentTransaction.getLoanId()).orElseThrow(() -> new RuntimeException("No loan data found for ID: "+paymentTransaction.getLoanId()));
        loan.setRepaymentDate(LocalDate.now());
        loan.setPaymentStatus("Paid");
        loan.setPaidDate(LocalDate.now());
        loanRepository.save(loan);
        paymentTransactionRepository.save(paymentTransaction);
    }
}

