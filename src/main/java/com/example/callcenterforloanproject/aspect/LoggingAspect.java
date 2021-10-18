package com.example.callcenterforloanproject.aspect;

import com.example.callcenterforloanproject.dto.LoanDto;
import com.example.callcenterforloanproject.model.Loan;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    @AfterThrowing(value = "execution(* com.example.callcenterforloanproject.service.LoanService.*(..))", throwing = "ex")
    public void logExceptionOfLoanService(JoinPoint joinPoint, Exception ex){
        LOGGER.error("Exception occurred in {}.{}() with cause = {} ", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), ex.getMessage());
    }
    @AfterThrowing(value = "execution(* com.example.callcenterforloanproject.service.CreditService.*(..))", throwing = "ex")
    public void logExceptionOfCreditService(JoinPoint joinPoint, Exception ex){
        LOGGER.error("Exception occurred in {}.{}() with cause = {} ", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), ex.getMessage());
    }
    @AfterThrowing(value = "execution(* com.example.callcenterforloanproject.service.ReclamService.*(..))", throwing = "ex")
    public void logExceptionOfReclamService(JoinPoint joinPoint, Exception ex){
        LOGGER.error("Exception occurred in {}.{}() with cause = {} ", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), ex.getMessage());
    }

    @AfterReturning(pointcut = "execution(* com.example.callcenterforloanproject.service.LoanService.addNewLoan(..))",
            returning = "loan")
    public void logAddNewLoanOfLoanService(JoinPoint jp, Loan loan){
        LOGGER.info("New loan added with id = {}, consumer name : {}, consumer surname : {}, address : {}, amount : {}, note : {}, credit name : {}, reclam type : {}",
                loan.getId(), loan.getName(), loan.getSurname(), loan.getAddress(), loan.getAmount(), loan.getNote(), loan.getCredit().getName(), loan.getReclame().getName());
    }
    @AfterReturning(pointcut = "execution(* com.example.callcenterforloanproject.service.LoanService.deleteLoanById(..))",
            returning = "loan")
    public void logDeleteLoanByIdOfLoanService(JoinPoint jp, Loan loan){
        LOGGER.warn("Current loan removed with id = {}, consumer name : {}, consumer surname : {}, address : {}, amount : {}, note : {}, credit name : {}, reclam type : {}",
                loan.getId(), loan.getName(), loan.getSurname(), loan.getAddress(), loan.getAmount(), loan.getNote(), loan.getCredit().getName(), loan.getReclame().getName());
    }
    @AfterReturning(pointcut = "execution(* com.example.callcenterforloanproject.service.LoanService.updateLoan(..))",
            returning = "loan")
    public void logUpdateLoanByIdOfLoanService(JoinPoint jp, Loan loan){
        LOGGER.warn("Current loan updated with id = {}, consumer name : {}, consumer surname : {}, address : {}, amount : {}, note : {}, credit name : {}, reclam type : {}",
                loan.getId(), loan.getName(), loan.getSurname(), loan.getAddress(), loan.getAmount(), loan.getNote(), loan.getCredit().getName(), loan.getReclame().getName());
    }
}
