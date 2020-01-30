package com.example.scoreboard.repository;

import com.example.scoreboard.document.ScoreOverall;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreOverallRepository extends MongoRepository<ScoreOverall , String> {
}
