package com.quizapp.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")  // allows frontend access
public class TestController {

    @GetMapping("/api/hello")
    public String sayHello() {
        return "Hello from Backend 👋";
    }
}
