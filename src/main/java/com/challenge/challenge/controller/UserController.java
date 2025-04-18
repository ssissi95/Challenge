package com.challenge.challenge.controller;

import com.challenge.challenge.dto.UserRequestDTO;
import com.challenge.challenge.dto.UserResponseDTO;
import com.challenge.challenge.model.User;
import com.challenge.challenge.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Endpoint para registrar un usuario
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRequestDTO userRequest, BindingResult result) {

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        try {
            // Convertir UserRequestDTO a User
            User user = convertToUser(userRequest);

            // Usamos el servicio para registrar el usuario
            user = userService.registerUser(user);

            // Convertimos el usuario a un DTO para enviar la respuesta
            UserResponseDTO response = new UserResponseDTO(user);

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            // Si hubo un error al registrar (ej. correo ya registrado)
            return new ResponseEntity<>(new ErrorResponse("El correo ya registrado"), HttpStatus.BAD_REQUEST);
        }
    }

    // Método auxiliar para convertir UserRequestDTO a User
    private User convertToUser(UserRequestDTO userRequest) {
        User user = new User();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        // Aquí deberías también mapear la lista de teléfonos, si es necesario
        // user.setPhones(userRequest.getPhones());
        user.setIsActive(true); // Asumimos que el usuario es activo por defecto
        user.setCreated(LocalDateTime.now());
        user.setModified(LocalDateTime.now());
        user.setLastLogin(LocalDateTime.now());
        user.setToken(UUID.randomUUID().toString()); // Generar un token
        return user;
    }

    static class ErrorResponse {
        private String mensaje;

        public ErrorResponse(String mensaje) {
            this.mensaje = mensaje;
        }

        public String getMensaje() {
            return mensaje;
        }

        public void setMensaje(String mensaje) {
            this.mensaje = mensaje;
        }
    }
}
