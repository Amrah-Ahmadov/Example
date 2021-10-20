package com.example.callcenterforloanproject.model.repository;

import com.example.callcenterforloanproject.model.entity.Reclame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReclamRepo extends JpaRepository<Reclame, Long> {
}
