package com.example.scoreboard.service;

import com.example.scoreboard.dto.SubmitStaticDTO;
import com.example.scoreboard.response.APIResponse;

public interface ScoreStaticService {
    APIResponse findStatic(String userId);
    void submitScore(SubmitStaticDTO submitStaticDTO , int id);
}
