package com.example.scoreboard.repository;

import com.example.scoreboard.document.Static4;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Static4Repository extends MongoRepository<Static4,String> {
}
