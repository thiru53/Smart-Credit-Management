package com.Application.Service;

import com.Application.entity.CreditLimit;
import com.Application.entity.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Application.Repository.CreditLimitRepository;
import com.Application.Repository.LoanRepository;
import com.Application.entity.LoanApplicationRequest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class LoanService {
    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private CreditLimitRepository creditLimitRepository;

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
        }

        List<CreditLimit> creditLimits = creditLimitRepository.findByBorrowerId(request.getBorrowerId());
        if(!creditLimits.isEmpty()){
            for (CreditLimit cl : creditLimits) {
                if (cl.getCreditLimit() < request.getLoanAmount() || cl.getRemainingAmount() < request.getLoanAmount()) {
                    throw new RuntimeException("Loan amount exceeds the remaining credit limit.");
                }
            }
        }
        Loan loan = populateNewLoanByRequest(request);
        loanRepository.save(loan);

        CreditLimit creditLimit = populateNewCreditLimitByRequest(request);
        creditLimitRepository.save(creditLimit);
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
}

