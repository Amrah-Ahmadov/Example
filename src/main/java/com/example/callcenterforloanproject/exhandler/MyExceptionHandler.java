package com.example.callcenterforloanproject.exhandler;

import com.example.callcenterforloanproject.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(CreditNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ExceptionClass> handleCreditNotFoundException(CreditNotFoundException creditNotFoundException){
        ExceptionClass ex = new ExceptionClass(creditNotFoundException.getMessage(), "404");
        return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(LoanNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ExceptionClass> handleLoanNotFoundException(LoanNotFoundException loanNotFoundException){
        ExceptionClass ex = new ExceptionClass(loanNotFoundException.getMessage(), "404");
        return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ReclameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ExceptionClass> handleReclameNotFoundException(ReclameNotFoundException reclameNotFoundException){
        ExceptionClass ex = new ExceptionClass(reclameNotFoundException.getMessage(), "404");
        return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(RepeatPasswordIncorrectException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionClass> handleRepeatPasswordIncorrectException(RepeatPasswordIncorrectException repeatPasswordIncorrectException){
        ExceptionClass ex = new ExceptionClass(repeatPasswordIncorrectException.getMessage(), "400");
        return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ExceptionClass> handleUserNotFoundException(UserNotFoundException userNotFoundException){
        ExceptionClass ex = new ExceptionClass(userNotFoundException.getMessage(), "404");
        return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
    }

}
