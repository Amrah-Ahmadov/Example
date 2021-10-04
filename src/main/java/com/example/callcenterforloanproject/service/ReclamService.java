package com.example.callcenterforloanproject.service;

import com.example.callcenterforloanproject.model.Reclame;
import com.example.callcenterforloanproject.repository.ReclamRepo;
import org.springframework.stereotype.Service;

@Service
public class ReclamService {
    private final ReclamRepo reclamRepo;

    public ReclamService(ReclamRepo reclamRepo) {
        this.reclamRepo = reclamRepo;
    }
    public Reclame addNewReclame(Reclame reclame){
        return reclamRepo.saveAndFlush(reclame);
    }

}
