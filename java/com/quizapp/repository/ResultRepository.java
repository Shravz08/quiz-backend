package com.quizapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.quizapp.model.Result;
import com.quizapp.model.User;
import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Long> {
	List<Result> findByUserId(Long userId);
	List<Result> findByQuizId(Long quizId);
	List<Result> findByUser(User user);
	List<Result> findAllByOrderByScoreDesc();
	
}