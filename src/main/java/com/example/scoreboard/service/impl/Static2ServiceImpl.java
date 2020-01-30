package com.example.scoreboard.service.impl;

import com.example.scoreboard.document.ScoreOverall;
import com.example.scoreboard.document.Static2;
import com.example.scoreboard.document.TempScore;
import com.example.scoreboard.dto.SubmitStaticDTO;
import com.example.scoreboard.repository.ScoreOverallRepository;
import com.example.scoreboard.repository.Static2Repository;
import com.example.scoreboard.repository.TempScoreRepository;
import com.example.scoreboard.service.Static2Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Static2ServiceImpl implements Static2Service {

    @Autowired
    Static2Repository static2Repository;

    @Autowired
    TempScoreRepository tempScoreRepository;

    @Autowired
    ScoreOverallRepository scoreOverallRepository;

    @Override
    public Static2 findStatic(String userId) {
        Optional<Static2> static2;
        static2= static2Repository.findById(userId);
        if(static2.isPresent()){
            Static2 static21 = new Static2();
            BeanUtils.copyProperties(static2.get(),static21);
            return static21;
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
        Static2 static2 = new Static2();
        static2.setUserId(submitStaticDTO.getUserId());
        Optional<ScoreOverall> scoreOverall;
        scoreOverall=scoreOverallRepository.findById(submitStaticDTO.getUserId());
        ScoreOverall scoreOverall1 = new ScoreOverall();
        BeanUtils.copyProperties(scoreOverall.get(), scoreOverall1 );
        if(!skip){
            static2.setScore(tempScore1.getScore()+5);
            scoreOverall1.setScore(scoreOverall1.getScore()+tempScore1.getScore()+5);
        }
        else {
            static2.setScore(tempScore1.getScore());
            scoreOverall1.setScore(scoreOverall1.getScore()+tempScore1.getScore());
        }
        scoreOverallRepository.save(scoreOverall1);
        static2Repository.save(static2);
        tempScoreRepository.deleteById(submitStaticDTO.getUserId());
    }
}
