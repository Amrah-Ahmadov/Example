package com.example.callcenterforloanproject.controller;

import com.example.callcenterforloanproject.dto.LoanDto;
import com.example.callcenterforloanproject.jwt.JWTManager;
import com.example.callcenterforloanproject.model.Credit;
import com.example.callcenterforloanproject.model.Loan;
import com.example.callcenterforloanproject.model.Reclame;
import com.example.callcenterforloanproject.model.User;
import com.example.callcenterforloanproject.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/loan")
public class LoanController {
    private final LoanService loanService;
    private final UserService userService;
    private final ConverterService converterService;
    private final JWTManager jwtManager;
    private final CreditService creditService;
    private final ReclamService reclamService;

    public LoanController(LoanService loanService, UserService userService, ConverterService converterService, JWTManager jwtManager, CreditService creditService, ReclamService reclamService) {
        this.loanService = loanService;
        this.userService = userService;
        this.converterService = converterService;
        this.jwtManager = jwtManager;
        this.creditService = creditService;
        this.reclamService = reclamService;
    }
    @PostMapping
    public ResponseEntity<LoanDto> addNewLoan(HttpServletRequest request, @RequestBody Loan loan){
        String userName = jwtManager.getUserNameFromToken(request.getHeader("Authorization").replace("Bearer ", ""));
        User user = userService.getUSerByUsername(userName);
//        Loan loan1 = loanService.addNewLoan(loan);
        Credit credit = creditService.getCreditById(loan.getCredit().getId());
//        loan1.setCredit(credit);
        loan.setCredit(credit); //// sonra elave olunan
        Reclame reclame = reclamService.getReclameByID(loan.getReclame().getId());
//        loan1.setReclame(reclame);
//        loan1.setUser(user);
//        loan1.setCreationTime(LocalDateTime.now());
        loan.setReclame(reclame);
        loan.setUser(user);
        loan.setCreationTime(LocalDateTime.now());
        Loan loan1 = loanService.addNewLoan(loan);
        return new ResponseEntity<>(converterService.convertLoanToLoanDto(loan1), HttpStatus.CREATED);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<LoanDto> deleteLoanById(@PathVariable Long id){
        Loan loan = loanService.deleteLoanById(id);
        return new ResponseEntity<>(converterService.convertLoanToLoanDto(loan), HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<LoanDto> getLoanById(HttpServletRequest request, @PathVariable Long id){
        Loan loan = loanService.getLoanById(id);
        return new ResponseEntity<>(converterService.convertLoanToLoanDto(loan), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<LoanDto>> getLoansByUser(){
        List<Loan> allLoans = loanService.getAllLoans();
        return new ResponseEntity<>(allLoans.stream().map(l -> converterService.convertLoanToLoanDto(l)).collect(Collectors.toList()), HttpStatus.OK);
    }

    @PutMapping("/{loanId}")
    public ResponseEntity<LoanDto> updateLoan(@PathVariable Long loanId, @RequestBody Loan loan){
        Loan loan1 = loanService.getLoanById(loanId);
        loan.setId(loanId); /////// deyise biler
        Credit credit = creditService.getCreditById(loan.getCredit().getId());
        loan.setCredit(credit);
        Reclame reclame = reclamService.getReclameByID(loan.getReclame().getId());
        loan.setReclame(reclame);
        loan.setUser(loan1.getUser());
        loan.setCreationTime(loan1.getCreationTime());
        Loan loan2 = loanService.updateLoan(loan);
        System.out.println(loan.getClass().getFields() + " XXXXXXXXX");
        return new ResponseEntity<>(converterService.convertLoanToLoanDto(loan2), HttpStatus.OK);
    }
}
