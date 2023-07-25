package com.Application.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.Application.Repository.LoanRepository;

@Service
public class CreditLimitScheduler {

    @Autowired
    private LoanService loanService;

    @Autowired
    private LoanRepository loanRepository;

    // Schedule the method to run at regular intervals
    @Scheduled(cron = "0 0 0 1 * ?") // Runs at midnight on the first day of every month
    public void scheduleCreditLimitUpdate() {


    }


}

