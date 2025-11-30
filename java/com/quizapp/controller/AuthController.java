package com.quizapp.controller;

import com.quizapp.model.User;
import com.quizapp.dto.*;
import com.quizapp.dto.RegisterRequest;
import com.quizapp.dto.JwtResponse;
import com.quizapp.repository.UserRepository;
import com.quizapp.security.JwtUtils;
import com.quizapp.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public JwtResponse authenticateUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(), loginRequest.getPassword()));

        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername());
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody RegisterRequest req) {

        if (userRepository.existsByUsername(req.getUsername())) {
            return "Username already taken";
        }

        User user = new User();
        user.setUsername(req.getUsername());
        user.setPassword(encoder.encode(req.getPassword()));

        userRepository.save(user);

        return "User registered successfully";
    }
}
