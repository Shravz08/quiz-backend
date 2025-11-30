package com.quizapp.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "quiz_attempts")
public class QuizAttempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable=false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", nullable=false)
    private Quiz quiz;

    private Integer score;
    private Integer total;
    private Instant attemptedAt = Instant.now();

    @Lob
    @Column(columnDefinition = "TEXT")
    private String answersJson;  // Store answers as JSON string if not using Postgres JSONB

    public QuizAttempt() {}

    public QuizAttempt(User user, Quiz quiz, Integer score, Integer total, String answersJson) {
        this.user = user;
        this.quiz = quiz;
        this.score = score;
        this.total = total;
        this.answersJson = answersJson;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Quiz getQuiz() { return quiz; }
    public void setQuiz(Quiz quiz) { this.quiz = quiz; }

    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }

    public Integer getTotal() { return total; }
    public void setTotal(Integer total) { this.total = total; }

    public Instant getAttemptedAt() { return attemptedAt; }
    public void setAttemptedAt(Instant attemptedAt) { this.attemptedAt = attemptedAt; }

    public String getAnswersJson() { return answersJson; }
    public void setAnswersJson(String answersJson) { this.answersJson = answersJson; }
}
