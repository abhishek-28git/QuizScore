package com.example.scoreboard.document;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class ScoreOverall {

    @Id
    private String userId;

    private int score;
}
