package com.example.callcenterforloanproject.controller;

import com.example.callcenterforloanproject.dto.CreditDto;
import com.example.callcenterforloanproject.dto.ReclameDto;
import com.example.callcenterforloanproject.model.Credit;
import com.example.callcenterforloanproject.model.Reclame;
import com.example.callcenterforloanproject.service.ConverterService;
import com.example.callcenterforloanproject.service.ReclamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("reclame")
public class ReclameController {
    private final ReclamService reclamService;
    private final ConverterService converterService;

    public ReclameController(ReclamService reclamService, ConverterService converterService) {
        this.reclamService = reclamService;
        this.converterService = converterService;
    }
    @PostMapping
    public ResponseEntity<ReclameDto> addNewReclame(@RequestBody Reclame reclame){
        Reclame reclame1 = reclamService.addNewReclame(reclame);
        return new ResponseEntity<>(converterService.convertReclameToReclameDto(reclame1), HttpStatus.CREATED);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<ReclameDto> deleteReclameById(@PathVariable Long id){
        Reclame reclame = reclamService.removeReclameById(id);
        return new ResponseEntity<>(converterService.convertReclameToReclameDto(reclame), HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<ReclameDto> getReclameById(@PathVariable Long id){
        Reclame reclame = reclamService.getReclameByID(id);
        return new ResponseEntity<>(converterService.convertReclameToReclameDto(reclame), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<ReclameDto>> getAllReclames(){
        List<Reclame> allReclames = reclamService.getAllReclames();
        return new ResponseEntity<>(allReclames.stream().map(l -> converterService.convertReclameToReclameDto(l)).collect(Collectors.toList()), HttpStatus.OK);
    }
}
