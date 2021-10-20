package com.example.callcenterforloanproject.service;

import com.example.callcenterforloanproject.dto.UserRegisterDto;
import com.example.callcenterforloanproject.model.entity.User;
import com.example.callcenterforloanproject.model.repository.IUserCriteriaRepo;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final ConverterService converterService;
    private final IUserCriteriaRepo iUserCriteriaRepo;

    public UserService(ConverterService converterService, IUserCriteriaRepo iUserCriteriaRepo) {
        this.converterService = converterService;
        this.iUserCriteriaRepo = iUserCriteriaRepo;
    }


    public User register(UserRegisterDto userRegisterDto){
        User user = converterService.convertUserRegisterDtoToUser(userRegisterDto);
        return iUserCriteriaRepo.saveUser(user);
    }
    public User getUSerByUsername(String username){
        return iUserCriteriaRepo.getUserByUserName(username);
    }
}
