package com.challenge.challenge.service;


import com.challenge.challenge.model.Phone;
import com.challenge.challenge.model.User;
import com.challenge.challenge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Value("${app.password.regex}")
    private String passwordRegex;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public User registerUser(User user) throws Exception {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new Exception("El correo ya registrado");
        }

        if (!Pattern.matches(passwordRegex, user.getPassword())) {
            throw new Exception("Contraseña inválida");
        }

        // Relacionar los teléfonos con el usuario
        if (user.getPhones() != null) {
            for (Phone phone : user.getPhones()) {
                phone.setUser(user);
            }
        }

        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }



}
