package com.example.scoreboard.service;

import com.example.scoreboard.document.Static1;
import com.example.scoreboard.dto.SubmitStaticDTO;
import com.example.scoreboard.response.APIResponse;

public interface Static1Service {
    Static1 findStatic(String userId);
    void submitScore(SubmitStaticDTO submitStaticDTO , boolean skip);
}
