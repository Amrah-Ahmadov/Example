package com.example.callcenterforloanproject.model.repository;

import com.example.callcenterforloanproject.model.entity.Reclame;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IReclamCriteriaRepo {
    Reclame saveReclame(Reclame reclame);
    Reclame deleteReclame(Reclame reclame);
    Reclame updateReclame(Reclame reclame);
    Reclame getReclameById(Long id);
    Reclame getReclameByName(String name);
    List<Reclame> getAllReclames();
}
