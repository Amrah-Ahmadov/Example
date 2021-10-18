package com.example.callcenterforloanproject.service;

import com.example.callcenterforloanproject.exception.CreditNotFoundException;
import com.example.callcenterforloanproject.model.Credit;
import com.example.callcenterforloanproject.model.Loan;
import com.example.callcenterforloanproject.repository.ICreditCriteriaRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CreditService {

    private final ICreditCriteriaRepo creditCriteriaRepo;

    public CreditService(ICreditCriteriaRepo creditCriteriaRepo) {
        this.creditCriteriaRepo = creditCriteriaRepo;
    }

    public Credit addNewCredit(Credit credit){
        return creditCriteriaRepo.saveCredit(credit);
    }
    public Credit removeCredit(Credit credit){
        if(credit != null){
            creditCriteriaRepo.deleteCredit(credit);
            return credit;
        }else{
            throw new CreditNotFoundException();
        }
    }
    public Credit removeCreditById(Long id){
        Optional<Credit> credit = Optional.ofNullable(creditCriteriaRepo.getCreditById(id));
        if(credit.isPresent()){
            creditCriteriaRepo.deleteCredit(credit.get());
            return credit.get();
        }else{
            throw new CreditNotFoundException();
        }
    }
    public Credit getCreditById(Long id){
        Optional<Credit> credit = Optional.ofNullable(creditCriteriaRepo.getCreditById(id));
        if(credit.isPresent()){
            return credit.get();
        }else{
            throw new CreditNotFoundException();
        }
    }
    public List<Credit> getAllCredits(){
        return creditCriteriaRepo.getAllCredits();
    }

}
