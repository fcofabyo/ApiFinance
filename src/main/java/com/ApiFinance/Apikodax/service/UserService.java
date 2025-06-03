package com.ApiFinance.Apikodax.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ApiFinance.Apikodax.dto.LoginDTO;
import com.ApiFinance.Apikodax.dto.UserDTO;
import com.ApiFinance.Apikodax.entity.User;
import com.ApiFinance.Apikodax.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // REGISTER NEW USER
    public User register(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        return userRepository.save(user);
    }

    // AUTHENTICATE USER
    public Optional<User> login(LoginDTO loginDTO) {
        Optional<User> user = userRepository.findByEmail(loginDTO.getEmail());

        if (user.isPresent()) {
            boolean passwordMatches = passwordEncoder.matches(
                    loginDTO.getPassword(),
                    user.get().getPassword());
            if (passwordMatches) {
                return user;
            }
        }

        return Optional.empty();
    }
}
