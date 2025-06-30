package com.example.auth.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column
    private String secretAnswer1;

    @Column
    private String secretAnswer2;

    @Column
    private String secretAnswer3;

    @Column
    private String secretAnswer4;

    @Column
    private int failedAttempts;

    @Column
    private LocalDateTime lockTime;
}
