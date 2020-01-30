package com.example.scoreboard.repository;

import com.example.scoreboard.document.TempScore;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FastestFingerFirst extends MongoRepository<TempScore,String> {
    List<TempScore> findByQuizIdAndQuestionId(String quizId,String questionId);

}
