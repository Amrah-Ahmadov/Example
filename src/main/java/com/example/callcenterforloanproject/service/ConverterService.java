package com.example.callcenterforloanproject.service;

import com.example.callcenterforloanproject.dto.*;
import com.example.callcenterforloanproject.model.entity.Credit;
import com.example.callcenterforloanproject.model.entity.Loan;
import com.example.callcenterforloanproject.model.entity.Reclame;
import com.example.callcenterforloanproject.model.entity.User;
import com.example.callcenterforloanproject.model.repository.CreditRepo;
import com.example.callcenterforloanproject.model.repository.ReclamRepo;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ConverterService {
    private final ModelMapper modelMapper;
//    private final ReclamRepo reclamRepo;
//    private final CreditRepo creditRepo;

    public ConverterService(ModelMapper modelMapper/*, ReclamRepo reclamRepo, CreditRepo creditRepo*/) {
        this.modelMapper = modelMapper;
//        this.reclamRepo = reclamRepo;
//        this.creditRepo = creditRepo;
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
        return modelMapper.map(user, UserDto.class);
    }
    public CreditDto convertCreditToCreditDto(Credit credit){
        return modelMapper.map(credit, CreditDto.class);
    }
    public ReclameDto convertReclameToReclameDto(Reclame reclame){
        return modelMapper.map(reclame, ReclameDto.class);
    }
    public LoanDto convertLoanToLoanDto(Loan loan){
        LoanDto loanDto = modelMapper.map(loan, LoanDto.class);
        loanDto.setCreditDto(convertCreditToCreditDto(loan.getCredit()));
        loanDto.setReclameDto(convertReclameToReclameDto(loan.getReclame()));
        loanDto.setUserDto(convertUserToUserDto(loan.getUser()));
        return loanDto;
    }
}
