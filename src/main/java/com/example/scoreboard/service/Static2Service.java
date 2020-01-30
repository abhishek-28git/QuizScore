package com.example.scoreboard.service;

import com.example.scoreboard.document.Static2;
import com.example.scoreboard.dto.SubmitStaticDTO;

public interface Static2Service {
    Static2 findStatic(String userId);
    void submitScore(SubmitStaticDTO submitStaticDTO , boolean skip);
}
