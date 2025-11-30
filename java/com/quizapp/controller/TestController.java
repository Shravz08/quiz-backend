package com.quizapp.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/test")
    public String test(@RequestParam String raw, @RequestParam String hashed) {
        boolean matches = passwordEncoder.matches(raw, hashed);
        return " OK : MATCH = " + matches;
    }
}
