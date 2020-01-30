package com.example.scoreboard.repository;


import com.example.scoreboard.document.ScoreStatic;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreStaticRepository extends MongoRepository<ScoreStatic,String> {
}
