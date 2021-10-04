package com.example.callcenterforloanproject.service;

import com.example.callcenterforloanproject.exception.CreditNotFoundException;
import com.example.callcenterforloanproject.model.Credit;
import com.example.callcenterforloanproject.repository.CreditRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreditService {

    private final CreditRepo creditRepo;

    public CreditService(CreditRepo creditRepo) {
        this.creditRepo = creditRepo;
    }

    public Credit addNewCredit(Credit credit){
        return creditRepo.saveAndFlush(credit);
    }
    public Credit removeCredit(Credit credit){
        if(credit != null){
            creditRepo.delete(credit);
            return credit;
        }else{
            throw new CreditNotFoundException();
        }
    }
    public Credit removeCreditById(Long id){
        Optional<Credit> credit = creditRepo.findById(id);
        if(credit.isPresent()){
            creditRepo.delete(credit.get());
            return credit.get();
        }else{
            throw new CreditNotFoundException();
        }
    }
    public Credit getCreditById(Long id){
        Optional<Credit> credit = creditRepo.findById(id);
        if(credit.isPresent()){
            return credit.get();
        }else{
            throw new CreditNotFoundException();
        }
    }

}
