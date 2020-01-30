package com.example.scoreboard.service.impl;

import com.example.scoreboard.document.ScoreOverall;
import com.example.scoreboard.response.APIResponse;
import com.example.scoreboard.service.ScoreOverallService;
import com.example.scoreboard.repository.ScoreOverallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScoreOverallServiceImpl implements ScoreOverallService {

    @Autowired
    private
    ScoreOverallRepository scoreOverallRepository;

    @Override
    public APIResponse findOverallById(String id) {
        Optional<ScoreOverall> scoreOverall = scoreOverallRepository.findById(id);
        if(scoreOverall.isPresent()) {
            APIResponse response = new APIResponse();
            response.setData(scoreOverall);
            response.setStatus(1000);
            response.setMessage("Le jaa Data");
            return response;
        }
        else
        {
            APIResponse response = new APIResponse();
            response.setStatus(200);
            response.setMessage("No Such Data");
            return response;
        }
    }

    @Override
    public APIResponse findAll() {
        APIResponse response = new APIResponse();
        List<ScoreOverall> leaderboard;
        leaderboard = scoreOverallRepository.findAll(Sort.by(Sort.Direction.DESC, "score"));
        response.setStatus(1000);
        response.setMessage("Leaderboard");
        response.setData(leaderboard);
        return response;
    }
}
