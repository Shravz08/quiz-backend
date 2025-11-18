package com.quizapp.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")  // Allow React frontend
public class QuizController {

    @GetMapping("/questions")
    public List<Map<String, Object>> getQuestions() {
        List<Map<String, Object>> questions = new ArrayList<>();

        Map<String, Object> q1 = new HashMap<>();
        q1.put("question", "What does JVM stand for?");
        q1.put("options", List.of("Java Virtual Machine", "Java Variable Method", "Just Virtual Machine", "Java Verified Mode"));
        q1.put("answer", 0);
        questions.add(q1);

        Map<String, Object> q2 = new HashMap<>();
        q2.put("question", "Which company developed Java?");
        q2.put("options", List.of("Microsoft", "Sun Microsystems", "Google", "IBM"));
        q2.put("answer", 1);
        questions.add(q2);

        return questions;
    }
}
