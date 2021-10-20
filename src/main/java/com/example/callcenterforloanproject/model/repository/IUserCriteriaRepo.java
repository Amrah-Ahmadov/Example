package com.example.callcenterforloanproject.model.repository;

import com.example.callcenterforloanproject.model.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IUserCriteriaRepo {
    User getUserById(Long id);
    User getUserByInnerNumber(Long innerNumber);
    List<User> getUserByName(String name);
    List<User> getUserBySurname(String surname);
    User getUserByUserName(String userName);
    User saveUser(User user);
    User updateUser(User user);
    User deleteUser(User user);
}
