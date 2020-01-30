package com.example.scoreboard.repository;

import com.example.scoreboard.document.Static3;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Static3Repository extends MongoRepository<Static3,String> {
}
