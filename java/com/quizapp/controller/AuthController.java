package com.quizapp.controller;

import com.quizapp.dto.LoginRequest;
import com.quizapp.dto.SignupRequest;
import com.quizapp.model.User;
import com.quizapp.repository.UserRepository;
import com.quizapp.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;

    // ---------------- LOGIN ----------------
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        
        System.out.println("LOGIN REQUEST -> username=" + loginRequest.getUsername());
        System.out.println("LOGIN REQUEST -> password=" + loginRequest.getPassword());

        // Generate JWT Token
        return jwtUtils.generateJwtToken(auth);
    }

    // ---------------- SIGNUP ----------------
    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequest signupRequest) {

        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            return "Username already taken";
        }

        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            return "Email already used";
        }

        User user = new User();
        user.setUsername(signupRequest.getUsername());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(encoder.encode(signupRequest.getPassword()));

        // Default role
        user.getRoles().add("ROLE_USER");

        userRepository.save(user);

        return "User registered successfully";
    }

}
