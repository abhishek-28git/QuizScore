package com.example.scoreboard.service.impl;

import com.example.scoreboard.document.ScoreOverall;
import com.example.scoreboard.document.Static5;
import com.example.scoreboard.document.TempScore;
import com.example.scoreboard.dto.SubmitStaticDTO;
import com.example.scoreboard.repository.ScoreOverallRepository;
import com.example.scoreboard.repository.Static5Repository;
import com.example.scoreboard.repository.TempScoreRepository;
import com.example.scoreboard.service.Static5Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Static5ServiceImpl implements Static5Service {

    @Autowired
    Static5Repository static5Repository;

    @Autowired
    TempScoreRepository tempScoreRepository;

    @Autowired
    ScoreOverallRepository scoreOverallRepository;

    @Override
    public Static5 findStatic(String userId) {
        Optional<Static5> static5;
        static5= static5Repository.findById(userId);
        if(static5.isPresent()){
            Static5 staticx = new Static5();
            BeanUtils.copyProperties(static5.get(),staticx);
            return staticx;
        }
        else
            return null;

    }

    @Override
    public void submitScore(SubmitStaticDTO submitStaticDTO , boolean skip) {
        Optional<TempScore> tempScore;
        tempScore = tempScoreRepository.findById(submitStaticDTO.getUserId());
        TempScore tempScore1 = new TempScore();
        BeanUtils.copyProperties(tempScore.get(), tempScore1);
        Static5 static5 = new Static5();
        static5.setUserId(submitStaticDTO.getUserId());
        Optional<ScoreOverall> scoreOverall;
        scoreOverall=scoreOverallRepository.findById(submitStaticDTO.getUserId());
        ScoreOverall scoreOverall1 = new ScoreOverall();
        BeanUtils.copyProperties(scoreOverall.get(), scoreOverall1 );
        if(!skip){
            static5.setScore(tempScore1.getScore()+5);
            scoreOverall1.setScore(scoreOverall1.getScore()+tempScore1.getScore()+5);
        }
        else {
            static5.setScore(tempScore1.getScore());
            scoreOverall1.setScore(scoreOverall1.getScore()+tempScore1.getScore());
        }
        scoreOverallRepository.save(scoreOverall1);
        static5Repository.save(static5);
        tempScoreRepository.deleteById(submitStaticDTO.getUserId());
    }
}
