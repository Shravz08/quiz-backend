package com.quizapp.dto;

import java.util.List;

public class QuestionDto {
    private Long id;
    private String text;
    private List<String> options;
    private String subject;

    public QuestionDto(Long id, String text, List<String> options, String subject) {
        this.id = id;
        this.text = text;
        this.options = options;
        this.subject = subject;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public List<String> getOptions() { return options; }
    public void setOptions(List<String> options) { this.options = options; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }
}
