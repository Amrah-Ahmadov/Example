package com.example.callcenterforloanproject.controller;

import com.example.callcenterforloanproject.dto.ReclameDto;
import com.example.callcenterforloanproject.model.Reclame;
import com.example.callcenterforloanproject.service.ConverterService;
import com.example.callcenterforloanproject.service.ReclamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
