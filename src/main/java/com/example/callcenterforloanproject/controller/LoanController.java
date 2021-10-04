package com.example.callcenterforloanproject.controller;

import com.example.callcenterforloanproject.dto.LoanDto;
import com.example.callcenterforloanproject.jwt.JWTManager;
import com.example.callcenterforloanproject.model.Loan;
import com.example.callcenterforloanproject.model.User;
import com.example.callcenterforloanproject.service.ConverterService;
import com.example.callcenterforloanproject.service.LoanService;
import com.example.callcenterforloanproject.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/loan")
public class LoanController {
    private final LoanService loanService;
    private final UserService userService;
    private final ConverterService converterService;
    private final JWTManager jwtManager;

    public LoanController(LoanService loanService, UserService userService, ConverterService converterService, JWTManager jwtManager) {
        this.loanService = loanService;
        this.userService = userService;
        this.converterService = converterService;
        this.jwtManager = jwtManager;
    }
    @PostMapping
    public ResponseEntity<LoanDto> addNewLoan(HttpServletRequest request, @RequestBody Loan loan){
        String userName = jwtManager.getUserNameFromToken(request.getHeader("Authorization").replace("Bearer ", ""));
        User user = userService.getUSerByUsername(userName);
        Loan loan1 = loanService.addNewLoan(loan);
        loan1.setUser(user);
        loan1.setCreationTime(LocalDateTime.now());
        return new ResponseEntity<>(converterService.convertLoanToLoanDto(loan1), HttpStatus.CREATED);
    }
}
