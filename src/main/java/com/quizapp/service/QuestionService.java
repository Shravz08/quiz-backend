package com.quizapp.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.quizapp.model.Question;
import com.quizapp.model.Quiz;
import com.quizapp.repository.QuestionRepository;
import com.quizapp.repository.QuizRepository;

import java.util.List;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuizRepository quizRepository;

    // 1️⃣ Get all questions
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    // 2️⃣ Get questions by quiz id
    public List<Question> getQuestionsByQuiz(Long quizId) {
        return questionRepository.findByQuizId(quizId);
    }

    // 3️⃣ Add new question (safe handling of nested quiz)
    public Question addQuestion(Question question) {
        if (question == null) {
            throw new RuntimeException("Question must not be null");
        }

        if (question.getQuiz() == null || question.getQuiz().getId() == null) {
            throw new RuntimeException("Quiz ID must be provided in the question JSON");
        }

        Long quizId = question.getQuiz().getId();
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found with id: " + quizId));

        question.setQuiz(quiz);
        return questionRepository.save(question);
    }

    // 4️⃣ Update question
    public Question updateQuestion(Long id, Question updatedQuestion) {
        if (updatedQuestion == null) {
            throw new RuntimeException("Updated question must not be null");
        }

        Question q = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found with id: " + id));

        // Update fields (assumes client sends the full updatedQuestion)
        q.setQuestionText(updatedQuestion.getQuestionText());
        q.setOption1(updatedQuestion.getOption1());
        q.setOption2(updatedQuestion.getOption2());
        q.setOption3(updatedQuestion.getOption3());
        q.setOption4(updatedQuestion.getOption4());
        q.setCorrectAnswer(updatedQuestion.getCorrectAnswer());

        // Update quiz if provided
        if (updatedQuestion.getQuiz() != null && updatedQuestion.getQuiz().getId() != null) {
            Long newQuizId = updatedQuestion.getQuiz().getId();
            Quiz quiz = quizRepository.findById(newQuizId)
                    .orElseThrow(() -> new RuntimeException("Quiz not found with id: " + newQuizId));
            q.setQuiz(quiz);
        }

        return questionRepository.save(q);
    }

    // 5️⃣ Delete question
    public void deleteQuestion(Long id) {
        if (!questionRepository.existsById(id)) {
            throw new RuntimeException("Question not found with id: " + id);
        }
        questionRepository.deleteById(id);
    }

    // 6️⃣ Generate random questions for a quiz
    public List<Question> getRandomQuestionsForQuiz(Long quizId, int numQuestions) {
        List<Question> allQuestions = questionRepository.findByQuizId(quizId);
        Collections.shuffle(allQuestions); // proper randomization
        return allQuestions.stream()
                .limit(numQuestions)
                .collect(Collectors.toList()); // works on Java 8+
    }
}
