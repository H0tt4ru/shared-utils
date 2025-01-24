package com.example.shared_utils.utils;

import org.springframework.stereotype.Component;

import com.example.base_domain.dto.model.User;
import com.example.base_domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserValidation {

    private final UserRepository userRepository;

    public boolean validateUserNew(User user) throws Exception {
        return validateUsername(user.getUsername()) &&
                validateEmail(user.getEmail()) &&
                validatePassword(user.getPassword());
    }

    public boolean validateUserUpdate(User user) throws Exception {
        return validateUsernameUpdate(user.getUsername()) &&
                validateEmailUpdate(user.getEmail()) &&
                validatePasswordUpdate(user.getPassword());
    }

    private boolean validateUsername(String username) throws Exception {
        if (username == null || username.length() > 20) {
            throw new Exception("4110");
        }
        return true;
    }

    private boolean validateEmail(String email) throws Exception {
        if (email == null || !email.contains("@")) {
            throw new Exception("4700");
        }
        if (!userRepository.findByEmail(email).isEmpty()) {
            throw new Exception("4103");
        }
        return true;
    }

    private boolean validatePassword(String password) throws Exception {
        if (password == null || password.length() < 8 || !password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$")) {
            throw new Exception("4104");
        }
        return true;
    }

    private boolean validateUsernameUpdate(String username) throws Exception {
        if (username != null && username.length() > 20) {
            throw new Exception("4105");
        }
        return true;
    }

    private boolean validateEmailUpdate(String email) throws Exception {
        if (email != null && !email.contains("@")) {
            throw new Exception("4700");
        }
        return true;
    }

    private boolean validatePasswordUpdate(String password) throws Exception {
        if (password != null && (password.length() < 8 || !password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$"))) {
            throw new Exception("4104");
        }
        return true;
    }

    public boolean validateEmailLogin(String email) throws Exception {
        if (email == null || !email.contains("@")) {
            throw new Exception("4700");
        }
        return true;
    }
}
