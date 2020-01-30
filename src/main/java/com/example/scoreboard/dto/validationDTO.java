package com.example.scoreboard.dto;

import lombok.Data;

import java.util.List;

@Data
public class validationDTO {

    private String  userId;
    private String questionId;
    private String quizId;
    private String answerType;
    private List<String> answers;
}
