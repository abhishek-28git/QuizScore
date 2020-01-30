package com.example.scoreboard.repository;

import com.example.scoreboard.document.Static2;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Static2Repository extends MongoRepository<Static2,String> {
}
