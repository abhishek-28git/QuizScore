package com.example.scoreboard.repository;

import com.example.scoreboard.document.TempScore;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TempScoreRepository extends MongoRepository<TempScore,String> {


}
