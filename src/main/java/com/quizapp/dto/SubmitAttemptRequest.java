package com.quizapp.dto;

import java.util.Map;

public class SubmitAttemptRequest {
    private String subject;
    private Integer score;
    private Integer total;
    private Map<Long, Integer> answers; // questionId -> chosenIndex

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }

    public Integer getTotal() { return total; }
    public void setTotal(Integer total) { this.total = total; }

    public Map<Long, Integer> getAnswers() { return answers; }
    public void setAnswers(Map<Long, Integer> answers) { this.answers = answers; }
}
