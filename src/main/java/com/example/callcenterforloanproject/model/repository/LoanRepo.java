package com.example.callcenterforloanproject.model.repository;

import com.example.callcenterforloanproject.model.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepo extends JpaRepository<Loan, Long> {
}
