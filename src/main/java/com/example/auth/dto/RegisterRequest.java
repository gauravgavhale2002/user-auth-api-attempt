package com.example.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequest {

    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String confirmPassword;

    @NotBlank
    private String secretAnswer1;

    @NotBlank
    private String secretAnswer2;

    @NotBlank
    private String secretAnswer3;

    @NotBlank
    private String secretAnswer4;
}
