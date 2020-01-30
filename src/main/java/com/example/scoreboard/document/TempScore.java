package com.example.scoreboard.document;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class TempScore {
    private String userId;
    private int score;
    private String quizId;
}
