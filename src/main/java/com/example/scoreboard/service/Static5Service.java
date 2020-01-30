package com.example.scoreboard.service;

import com.example.scoreboard.document.Static5;
import com.example.scoreboard.dto.SubmitStaticDTO;

public interface Static5Service {
    Static5 findStatic(String userId);
    void submitScore(SubmitStaticDTO submitStaticDTO , boolean skip);
}
