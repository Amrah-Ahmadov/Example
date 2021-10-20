package com.example.callcenterforloanproject.jwt;

import com.example.callcenterforloanproject.model.entity.User;
import com.example.callcenterforloanproject.model.repository.IUserCriteriaRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private final IUserCriteriaRepo iUserCriteriaRepo;

    public MyUserDetailsService(IUserCriteriaRepo iUserCriteriaRepo) {
        this.iUserCriteriaRepo = iUserCriteriaRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = iUserCriteriaRepo.getUserByUserName(s);
        if(user != null){
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
        }else{
            throw new UsernameNotFoundException("user not found with username: " + s);
        }
    }
}
