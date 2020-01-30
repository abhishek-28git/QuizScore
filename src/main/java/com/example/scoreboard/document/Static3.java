package com.example.scoreboard.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Static3 {
    @Id
    private String userId;
    private int score;
}
