package com.example.callcenterforloanproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.OK,reason = "Username or password is incorrect")
public class UsernameOrPasswordIncorrectException extends RuntimeException{
    public UsernameOrPasswordIncorrectException() {
        super("Username or password is incorrect please try again");
    }
}
