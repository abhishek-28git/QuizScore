package com.example.scoreboard.service;

import com.example.scoreboard.dto.validationDTO;
import com.example.scoreboard.response.APIResponse;


public interface TempScoreService {
    public void updateScore(validationDTO validationDTO, int marks);
}
