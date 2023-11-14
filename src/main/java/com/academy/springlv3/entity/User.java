package com.academy.springlv3.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    private String part;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum auth;

    public User(String email, String password, String part, UserRoleEnum auth) {
        this.email = email;
        this.password = password;
        this.part = part;
        this.auth = auth;
    }
}
