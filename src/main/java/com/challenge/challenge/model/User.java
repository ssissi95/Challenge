package com.challenge.challenge.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private String id = UUID.randomUUID().toString();

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private LocalDateTime created = LocalDateTime.now();
    private LocalDateTime modified = LocalDateTime.now();
    private LocalDateTime lastLogin = LocalDateTime.now();

    private String token = UUID.randomUUID().toString();

    private boolean isActive;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Phone> phones;

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean b) {
    }
}
