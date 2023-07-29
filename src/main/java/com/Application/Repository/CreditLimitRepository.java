package com.Application.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Application.entity.CreditLimit;

@Repository
public interface CreditLimitRepository extends JpaRepository<CreditLimit, Long> {


    // You can add custom query methods if needed
    CreditLimit findByBorrowerId(String borrowerId);
}

