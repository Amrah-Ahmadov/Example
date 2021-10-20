package com.example.callcenterforloanproject.service;

import com.example.callcenterforloanproject.exception.LoanNotFoundException;
import com.example.callcenterforloanproject.model.entity.Loan;
import com.example.callcenterforloanproject.model.repository.ILoanCriteriaRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanService {
    private final ILoanCriteriaRepo loanCriteriaRepo;

    public LoanService(ILoanCriteriaRepo loanCriteriaRepo) {
        this.loanCriteriaRepo = loanCriteriaRepo;
    }

    public Loan addNewLoan(Loan loan){
        Loan loan1 = loanCriteriaRepo.saveLoan(loan);
        return loan1;      //sonra deyis!!
    }
    public Loan deleteLoanById(Long id){
        Optional<Loan> loan = Optional.ofNullable(loanCriteriaRepo.getLoanById(id));
        if(loan.isPresent()){
            return loanCriteriaRepo.deleteLoan(loan.get());
        }else{
            throw new LoanNotFoundException();
        }
    }
    public Loan getLoanById(Long id){
        Optional<Loan> loan = Optional.ofNullable(loanCriteriaRepo.getLoanById(id));
        if(loan.isPresent()){
            return loan.get();
        }else{
            throw new LoanNotFoundException();
        }
    }
    public List<Loan> getAllLoans(){
        return loanCriteriaRepo.getAllLoans();
    }
    public Loan updateLoan(Loan loan){
       return loanCriteriaRepo.updateLoan(loan);
    }
}
