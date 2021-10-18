package com.example.callcenterforloanproject.repository;

import com.example.callcenterforloanproject.model.Credit;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICreditCriteriaRepo {
    Credit saveCredit(Credit credit);
    Credit deleteCredit(Credit credit);
    Credit updateCredit(Credit credit);
    Credit getCreditById(Long id);
    Credit getCreditByName(String name);
    List<Credit> getAllCredits();
}
