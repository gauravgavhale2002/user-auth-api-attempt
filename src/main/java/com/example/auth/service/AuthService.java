package com.example.auth.service;

import com.example.auth.dto.LoginRequest;
import com.example.auth.entity.User;
import com.example.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    public String login(LoginRequest request) {
        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());
        if (userOpt.isEmpty()) {
            logger.warn("Login failed: User not found for email {}", request.getEmail());
            return "❌ Invalid credentials";
        }

        User user = userOpt.get();

        // Lock check
        if (user.getLockTime() != null) {
            LocalDateTime now = LocalDateTime.now();
            if (now.isBefore(user.getLockTime().plusSeconds(30))) {
                long secondsLeft = Duration.between(now, user.getLockTime().plusSeconds(30)).getSeconds();
                logger.warn("User {} is locked. Time left: {} seconds", request.getEmail(), secondsLeft);
                return "❌ Account locked. Try again in " + secondsLeft + " seconds.";
            } else {
                logger.info("User {} unlocked after timeout", request.getEmail());
                user.setLockTime(null);
                user.setFailedAttempts(0);
            }
        }

        // Wrong password
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            int attempts = user.getFailedAttempts() + 1;
            user.setFailedAttempts(attempts);
            logger.warn("Wrong password for user {}. Attempts = {}", request.getEmail(), attempts);

            if (attempts >= 3) {
                user.setLockTime(LocalDateTime.now());
                userRepository.save(user);
                logger.error("User {} locked after 3 failed attempts", request.getEmail());
                return "❌ 3 wrong login attempts! Account locked for 30 seconds.";
            }

            userRepository.save(user);
            return "❌ Invalid credentials. Attempts left: " + (3 - attempts);
        }

        // Success
        logger.info("User {} logged in successfully", request.getEmail());
        user.setFailedAttempts(0);
        user.setLockTime(null);
        userRepository.save(user);

        return "✅ Login successful. Welcome " + user.getEmail();
    }
}