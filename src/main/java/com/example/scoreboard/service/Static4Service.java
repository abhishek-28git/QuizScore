package com.example.scoreboard.service;

import com.example.scoreboard.document.Static4;
import com.example.scoreboard.dto.SubmitStaticDTO;

public interface Static4Service {
    Static4 findStatic(String userId);
    void submitScore(SubmitStaticDTO submitStaticDTO , boolean skip);
}
