package com.example.callcenterforloanproject.controller;

import com.example.callcenterforloanproject.dto.CreditDto;
import com.example.callcenterforloanproject.model.Credit;
import com.example.callcenterforloanproject.service.ConverterService;
import com.example.callcenterforloanproject.service.CreditService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/credit")
public class CreditController {
    private final CreditService creditService;
    private final ConverterService converterService;

    public CreditController(CreditService creditService, ConverterService converterService) {
        this.creditService = creditService;
        this.converterService = converterService;
    }
    @PostMapping
    public ResponseEntity<CreditDto> addNewCredit(@RequestBody Credit credit){
        Credit credit1 = creditService.addNewCredit(credit);
        CreditDto creditDto = converterService.convertCreditToCreditDto(credit1);
        return new ResponseEntity<>(creditDto, HttpStatus.OK);
    }
}
