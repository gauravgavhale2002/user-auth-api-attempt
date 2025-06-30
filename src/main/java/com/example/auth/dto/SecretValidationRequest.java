package com.example.auth.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class SecretValidationRequest {
    private String email;
    private String secretAnswer1;
    private String secretAnswer2;
    private String secretAnswer3;
    private String secretAnswer4;
    private String newPassword;
    private String confirmPassword;
}
