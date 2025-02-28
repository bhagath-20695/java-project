package com.example.creditkarma.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // ✅ Primary key

    @Column(unique = true, nullable = false)
    private String username;

    private String password;

    @Column(nullable = false, unique = true)
    private String userId;  // ✅ Unique User ID generated during registration
}
