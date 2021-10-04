package com.example.callcenterforloanproject.repository;

import com.example.callcenterforloanproject.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepo extends JpaRepository<Loan, Long> {
}
