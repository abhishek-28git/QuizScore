package com.example.scoreboard.service;

import com.example.scoreboard.document.ScoreOverall;
import com.example.scoreboard.response.APIResponse;

import java.util.List;

public interface ScoreOverallService {

    ScoreOverall findOverallById(String id);

    List<ScoreOverall> findAll();
}
