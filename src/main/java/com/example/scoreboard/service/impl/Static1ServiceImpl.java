package com.example.scoreboard.service.impl;

import com.example.scoreboard.document.ScoreOverall;
import com.example.scoreboard.document.Static1;
import com.example.scoreboard.document.TempScore;
import com.example.scoreboard.dto.SubmitStaticDTO;
import com.example.scoreboard.repository.ScoreOverallRepository;
import com.example.scoreboard.repository.TempScoreRepository;
import com.example.scoreboard.service.Static1Service;
import com.example.scoreboard.repository.Static1Repository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Static1ServiceImpl implements Static1Service {

    @Autowired
    Static1Repository static1Repository;

    @Autowired
    TempScoreRepository tempScoreRepository;

    @Autowired
    ScoreOverallRepository scoreOverallRepository;

    @Override
    public Static1 findStatic(String userId) {
        Optional<Static1> static1;
        static1= static1Repository.findById(userId);
        if(static1.isPresent()){
            Static1 static2 = new Static1();
            BeanUtils.copyProperties(static1.get(),static2);
            return static2;
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
        Static1 static1 = new Static1();
        static1.setUserId(submitStaticDTO.getUserId());
        Optional<ScoreOverall> scoreOverall;
        scoreOverall=scoreOverallRepository.findById(submitStaticDTO.getUserId());
        ScoreOverall scoreOverall1 = new ScoreOverall();
        BeanUtils.copyProperties(scoreOverall.get(), scoreOverall1 );
        if(!skip){
            static1.setScore(tempScore1.getScore()+5);
            scoreOverall1.setScore(scoreOverall1.getScore()+tempScore1.getScore()+5);
        }
        else {
            static1.setScore(tempScore1.getScore());
            scoreOverall1.setScore(scoreOverall1.getScore()+tempScore1.getScore());
        }
        scoreOverallRepository.save(scoreOverall1);
        static1Repository.save(static1);
        tempScoreRepository.deleteById(submitStaticDTO.getUserId());
    }
}
