package com.example.callcenterforloanproject.service;

import com.example.callcenterforloanproject.exception.ReclameNotFoundException;
import com.example.callcenterforloanproject.model.Reclame;
import com.example.callcenterforloanproject.repository.IReclamCriteriaRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReclamService {
    private final IReclamCriteriaRepo reclamCriteriaRepo;

    public ReclamService(IReclamCriteriaRepo reclamCriteriaRepo) {
        this.reclamCriteriaRepo = reclamCriteriaRepo;
    }


    public Reclame addNewReclame(Reclame reclame){
        return reclamCriteriaRepo.saveReclame(reclame);
    }
    public Reclame getReclameByID(Long id){
        Optional<Reclame> reclame = Optional.ofNullable(reclamCriteriaRepo.getReclameById(id));
        if(reclame.isPresent()){
            return reclame.get();
        }
        throw new ReclameNotFoundException();
    }
}
