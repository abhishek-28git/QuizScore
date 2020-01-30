package com.example.scoreboard.service;

import com.example.scoreboard.response.APIResponse;

public interface ScoreOverallService {

    APIResponse findOverallById(String id);

    APIResponse findAll();
}
