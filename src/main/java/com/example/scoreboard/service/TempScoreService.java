package com.example.scoreboard.service;

import com.example.scoreboard.dto.ValidationDTO;


public interface TempScoreService {
    public void updateScore(ValidationDTO ValidationDTO, int marks);
}
