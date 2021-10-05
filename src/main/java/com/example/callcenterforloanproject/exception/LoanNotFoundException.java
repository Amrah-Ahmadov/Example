package com.example.callcenterforloanproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "No such a Loan found")
public class LoanNotFoundException extends RuntimeException{
    public LoanNotFoundException() {
        super("No such a Loan found");
    }
}
