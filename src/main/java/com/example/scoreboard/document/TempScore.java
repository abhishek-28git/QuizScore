package com.example.scoreboard.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Time;

@Data
@Document
//todo : question level details
// todo : date of answer is missing
//todo : how do you know from this model, who is the first one answered the question to give marks for dynamic quiz
public class TempScore {
    @Id
    private String userId;
    private int score;
    private String quizId;
//    private String questionId;
    private Time time;
}
