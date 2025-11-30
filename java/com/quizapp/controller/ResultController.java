package com.quizapp.controller;

import com.quizapp.model.Result;
import com.quizapp.model.User;
import com.quizapp.repository.ResultRepository;
import com.quizapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/results")
public class ResultController {

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private UserRepository userRepository;

    // 1️⃣ Get all results
    @GetMapping
    public List<Result> getAllResults() {
        return resultRepository.findAll();
    }

    // 2️⃣ Get results by userId
    @GetMapping("/user/{userId}")
    public List<Result> getResultsByUserId(@PathVariable Long userId) {
        // ✅ Fetch User object first
        User user = userRepository.findById(userId).orElse(null);

        // ✅ Then query by user object
        return resultRepository.findByUser(user);
    }

    // 3️⃣ Add a new result
    @PostMapping("/result")
    public Result addResult(@RequestBody Result result) {
        return resultRepository.save(result);
    }

    // 4️⃣ Delete a result
    @DeleteMapping("/{id}")
    public String deleteResult(@PathVariable Long id) {
        resultRepository.deleteById(id);
        return "Result deleted successfully!";
    }
    
 // 5️⃣ Get leaderboard (Top Scores)
    @GetMapping("/leaderboard")
    public List<Result> getLeaderboard() {
        return resultRepository.findAllByOrderByScoreDesc();
    }

}
