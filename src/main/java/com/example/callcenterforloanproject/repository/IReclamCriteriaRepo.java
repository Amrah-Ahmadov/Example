package com.example.callcenterforloanproject.repository;

import com.example.callcenterforloanproject.model.Reclame;
import org.springframework.stereotype.Repository;

@Repository
public interface IReclamCriteriaRepo {
    Reclame saveReclame(Reclame reclame);
    Reclame deleteReclame(Reclame reclame);
    Reclame updateReclame(Reclame reclame);
    Reclame getReclameById(Long id);
    Reclame getReclameByName(String name);
}
