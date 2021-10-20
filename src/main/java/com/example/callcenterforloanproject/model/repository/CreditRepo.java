package com.example.callcenterforloanproject.model.repository;

import com.example.callcenterforloanproject.model.entity.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditRepo extends JpaRepository<Credit, Long> {
}
