package com.example.callcenterforloanproject.repository;

import com.example.callcenterforloanproject.model.Reclam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReclamRepo extends JpaRepository<Reclam, Long> {
}
