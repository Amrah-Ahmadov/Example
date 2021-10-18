package com.example.callcenterforloanproject.service;

import com.example.callcenterforloanproject.exception.CreditNotFoundException;
import com.example.callcenterforloanproject.exception.ReclameNotFoundException;
import com.example.callcenterforloanproject.model.Credit;
import com.example.callcenterforloanproject.model.Loan;
import com.example.callcenterforloanproject.model.Reclame;
import com.example.callcenterforloanproject.repository.IReclamCriteriaRepo;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public Reclame removeReclameById(Long id){
        Optional<Reclame> reclame = Optional.ofNullable(reclamCriteriaRepo.getReclameById(id));
        if(reclame.isPresent()){
            reclamCriteriaRepo.deleteReclame(reclame.get());
            return reclame.get();
        }else{
            throw new ReclameNotFoundException();
        }
    }
    public List<Reclame> getAllReclames(){
        return reclamCriteriaRepo.getAllReclames();
    }
}
