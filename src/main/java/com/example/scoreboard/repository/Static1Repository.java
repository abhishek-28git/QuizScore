package com.example.scoreboard.repository;


import com.example.scoreboard.document.Static1;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Static1Repository extends MongoRepository<Static1,String> {
}
