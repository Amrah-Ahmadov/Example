package com.example.callcenterforloanproject.service;

import com.example.callcenterforloanproject.model.Loan;
import com.example.callcenterforloanproject.repository.LoanRepo;
import org.springframework.stereotype.Service;

@Service
public class LoanService {
    private final LoanRepo loanRepo;

    public LoanService(LoanRepo loanRepo) {
        this.loanRepo = loanRepo;
    }
    public Loan addNewLoan(Loan loan){
        return loanRepo.saveAndFlush(loan);
    }
}
