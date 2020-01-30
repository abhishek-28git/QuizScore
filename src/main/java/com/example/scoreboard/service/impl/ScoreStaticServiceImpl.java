package com.example.scoreboard.service.impl;

import com.example.scoreboard.document.ScoreOverall;
import com.example.scoreboard.document.ScoreStatic;
import com.example.scoreboard.document.TempScore;
import com.example.scoreboard.dto.SubmitStaticDTO;
import com.example.scoreboard.repository.ScoreOverallRepository;
import com.example.scoreboard.repository.TempScoreRepository;
import com.example.scoreboard.response.APIResponse;
import com.example.scoreboard.service.ScoreStaticService;
import com.example.scoreboard.repository.ScoreStaticRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ScoreStaticServiceImpl implements ScoreStaticService {

    @Autowired
    ScoreStaticRepository ScoreStaticRepository;

    @Autowired
    TempScoreRepository tempScoreRepository;

    @Autowired
    ScoreOverallRepository scoreOverallRepository;

    @Override
    public APIResponse findStatic(String userId) {
        Optional<ScoreStatic> scoreStatic;
        scoreStatic= ScoreStaticRepository.findById(userId);
        if(scoreStatic.isPresent()) {
            APIResponse response = new APIResponse();
            response.setData(scoreStatic);
            response.setStatus(1000);
            response.setMessage("Le jaa Data");
            return response;
        }
        else {
            APIResponse response = new APIResponse();
            response.setData(scoreStatic);
            response.setStatus(200);
            response.setMessage("No Such Data");
            return response;
        }
    }

    @Override
    public void submitScore(SubmitStaticDTO submitStaticDTO , int id) {
        Optional<TempScore> tempScore;
        tempScore = tempScoreRepository.findById(submitStaticDTO.getUserId());
        TempScore tempScore1 = new TempScore();
        BeanUtils.copyProperties(tempScore, tempScore1);
        ScoreStatic scoreStatic = new ScoreStatic();
        scoreStatic.setUserId(submitStaticDTO.getUserId());
        if(id==1)
        {
            if(scoreStatic.getS1()<tempScore1.getScore())
                scoreStatic.setS1(tempScore1.getScore());
        }
        else if(id==2)
        {
            if(scoreStatic.getS2()<tempScore1.getScore())
                scoreStatic.setS2(tempScore1.getScore());
        }
        else if(id==3)
        {
            if(scoreStatic.getS3()<tempScore1.getScore())
                scoreStatic.setS3(tempScore1.getScore());
        }
        else if(id==4)
        {
            if(scoreStatic.getS4() < tempScore1.getScore())
                scoreStatic.setS4(tempScore1.getScore());
        }
        else if(id==5)
        {
            if(scoreStatic.getS5() < tempScore1.getScore())
                scoreStatic.setS5(tempScore1.getScore());
        }
        else if(id==6)
        {
            if(scoreStatic.getS6() < tempScore1.getScore())
                scoreStatic.setS6(tempScore1.getScore());
        }
        Optional<ScoreOverall> scoreOverall;
        scoreOverall=scoreOverallRepository.findById(submitStaticDTO.getUserId());
        ScoreOverall scoreOverall1 = new ScoreOverall();
        BeanUtils.copyProperties(scoreOverall, scoreOverall1 );
        scoreOverall1.setScore(scoreOverall1.getScore()+tempScore1.getScore());
        scoreOverallRepository.save(scoreOverall1);

        ScoreStaticRepository.save(scoreStatic);
        tempScoreRepository.deleteById(submitStaticDTO.getUserId());

    }
}
