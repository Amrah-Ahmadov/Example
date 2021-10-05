package com.example.callcenterforloanproject.repository;

import com.example.callcenterforloanproject.model.Loan;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ILoanCriteriaRepo {
    Loan saveLoan(Loan loan);
    Loan deleteLoan(Loan loan);
    Loan updateLoan(Loan loan);
    Loan getLoanById(Long id);
    List<Loan> getLoanByCustomerName(String name);
    List<Loan> getLoanByCustomerSurname(String surname);
    List<Loan> getLoanByCustomerNameAndSurname(String name, String surname);
    List<Loan> getLoanByCustomerAddress(String address);
    List<Loan> getLoanByCustomerAmount(BigDecimal amount);
    List<Loan> getLoanByCustomerNote(String note);
    List<Loan> getLoanByCreditId(Long id);
    List<Loan> getLoanByReclameId(Long id);
    List<Loan> getLoanByUserId(Long id);
    List<Loan> getAllLoans();
}
