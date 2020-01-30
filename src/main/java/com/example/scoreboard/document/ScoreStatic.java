package com.example.scoreboard.document;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class ScoreStatic {

    @Id
    private String userId;
    private int S1;
    private int S2;
    private int S3;
    private int S4;
    private int S5;
    private int S6;
}
