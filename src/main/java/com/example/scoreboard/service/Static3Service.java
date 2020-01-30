package com.example.scoreboard.service;

import com.example.scoreboard.document.Static3;
import com.example.scoreboard.dto.SubmitStaticDTO;

public interface Static3Service {
    Static3 findStatic(String userId);
    void submitScore(SubmitStaticDTO submitStaticDTO , boolean skip);
}
