package com.challenge.challenge.service;

import com.challenge.challenge.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User registerUser(User user) throws Exception;
    List<User> getAllUsers();

    void deleteAllUsers();



}