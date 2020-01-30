package com.example.scoreboard.repository;

import com.example.scoreboard.document.ScoreDynamic;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreDynamicRepository extends MongoRepository<ScoreDynamic , String> {
}
