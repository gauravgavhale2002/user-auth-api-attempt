package com.example.auth.service;

import com.example.auth.dto.RegisterRequest;
import com.example.auth.dto.SecretValidationRequest;
import com.example.auth.entity.User;
import com.example.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;




import java.time.Duration;
import java.time.LocalDateTime;



@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public String register(RegisterRequest request) {
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            return "❌ Passwords do not match";
        }

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return "❌ Email already exists";
        }

        if (!isPasswordStrong(request.getPassword())) {
            return "❌ Password must contain uppercase, lowercase, digit, and special character";
        }

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .secretAnswer1(passwordEncoder.encode(request.getSecretAnswer1()))
                .secretAnswer2(passwordEncoder.encode(request.getSecretAnswer2()))
                .secretAnswer3(passwordEncoder.encode(request.getSecretAnswer3()))
                .secretAnswer4(passwordEncoder.encode(request.getSecretAnswer4()))
                .build();

        userRepository.save(user);
        return "✅ Registered successfully";
    }

    @Transactional
    public String resetPasswordWithSecrets(SecretValidationRequest request) {
        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());
        if (userOpt.isEmpty()) return "❌ User not found";

        User user = userOpt.get();

        // Check if locked
        if (user.getLockTime() != null) {
            LocalDateTime now = LocalDateTime.now();
            if (now.isBefore(user.getLockTime().plusSeconds(30))) {
                long secondsLeft = Duration.between(now, user.getLockTime().plusSeconds(30)).getSeconds();
                return "❌ Account locked. Try again in " + secondsLeft + " seconds.";
            } else {
                // Unlock after timeout
                user.setLockTime(null);
                user.setFailedAttempts(0);
            }
        }

        boolean allMatch = passwordEncoder.matches(request.getSecretAnswer1(), user.getSecretAnswer1()) &&
                passwordEncoder.matches(request.getSecretAnswer2(), user.getSecretAnswer2()) &&
                passwordEncoder.matches(request.getSecretAnswer3(), user.getSecretAnswer3()) &&
                passwordEncoder.matches(request.getSecretAnswer4(), user.getSecretAnswer4());

        if (!allMatch) {
            int attempts = user.getFailedAttempts() + 1;
            user.setFailedAttempts(attempts);

            if (attempts >= 3) {
                user.setLockTime(LocalDateTime.now());
                userRepository.save(user);
                return "❌ 3 wrong attempts! Account locked for 30 seconds.";
            }

            userRepository.save(user);
            return "❌ Incorrect answers. Attempts left: " + (3 - attempts);
        }

        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            return "❌ Passwords do not match";
        }

        if (!isPasswordStrong(request.getNewPassword())) {
            return "❌ Password must contain uppercase, lowercase, digit, and special character";
        }

        // ✅ Correct — reset everything
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        user.setFailedAttempts(0);
        user.setLockTime(null);
        userRepository.save(user);

        return "✅ Password reset successful";
    }

    @Transactional
    private boolean isPasswordStrong(String password) {
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{6,}$");
    }
}
