package com.example.callcenterforloanproject.service;

import com.example.callcenterforloanproject.dto.*;
import com.example.callcenterforloanproject.model.Credit;
import com.example.callcenterforloanproject.model.Loan;
import com.example.callcenterforloanproject.model.Reclame;
import com.example.callcenterforloanproject.model.User;
import com.example.callcenterforloanproject.repository.CreditRepo;
import com.example.callcenterforloanproject.repository.ReclamRepo;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConverterService {
    private final ModelMapper modelMapper;
    private final ReclamRepo reclamRepo;
    private final CreditRepo creditRepo;

    public ConverterService(ModelMapper modelMapper, ReclamRepo reclamRepo, CreditRepo creditRepo) {
        this.modelMapper = modelMapper;
        this.reclamRepo = reclamRepo;
        this.creditRepo = creditRepo;
    }

    public User convertUserRegisterDtoToUser(UserRegisterDto userRegisterDto){
        return User.builder()
                .name(userRegisterDto.getName())
                .innerNumber(userRegisterDto.getInnerNumber())
                .surname(userRegisterDto.getSurname())
                .username(userRegisterDto.getUsername())
                .password( new BCryptPasswordEncoder().encode(userRegisterDto.getPassword()))
                .build();
    }
    public UserDto convertUserToUserDto(User user){
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }
    public CreditDto convertCreditToCreditDto(Credit credit){
        CreditDto creditDto = modelMapper.map(credit, CreditDto.class);
        return creditDto;
    }
    public ReclameDto convertReclameToReclameDto(Reclame reclame){
        ReclameDto reclameDto = modelMapper.map(reclame, ReclameDto.class);
        return reclameDto;
    }
    public LoanDto convertLoanToLoanDto(Loan loan){
        LoanDto loanDto = modelMapper.map(loan, LoanDto.class);
        loanDto.setCreditDto(convertCreditToCreditDto(loan.getCredit()));
        loanDto.setReclameDto(convertReclameToReclameDto(loan.getReclame()));
        loanDto.setUserDto(convertUserToUserDto(loan.getUser()));
        return loanDto;
    }
}
