package com.example.scoreboard.service.impl;

import com.example.scoreboard.document.ScoreOverall;
import com.example.scoreboard.document.Static3;
import com.example.scoreboard.document.TempScore;
import com.example.scoreboard.dto.SubmitStaticDTO;
import com.example.scoreboard.repository.ScoreOverallRepository;
import com.example.scoreboard.repository.Static3Repository;
import com.example.scoreboard.repository.TempScoreRepository;
import com.example.scoreboard.service.Static3Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Static3ServiceImpl implements Static3Service {

    @Autowired
    Static3Repository static3Repository;

    @Autowired
    TempScoreRepository tempScoreRepository;

    @Autowired
    ScoreOverallRepository scoreOverallRepository;

    @Override
    public Static3 findStatic(String userId) {
        Optional<Static3> static3;
        static3 = static3Repository.findById(userId);
        if(static3.isPresent()){
            Static3 staticx = new Static3();
            BeanUtils.copyProperties(static3.get(),staticx);
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
        Static3 static3 = new Static3();
        static3.setUserId(submitStaticDTO.getUserId());
        Optional<ScoreOverall> scoreOverall;
        scoreOverall=scoreOverallRepository.findById(submitStaticDTO.getUserId());
        ScoreOverall scoreOverall1 = new ScoreOverall();
        BeanUtils.copyProperties(scoreOverall.get(), scoreOverall1 );
        if(!skip){
            static3.setScore(tempScore1.getScore()+5);
            scoreOverall1.setScore(scoreOverall1.getScore()+tempScore1.getScore()+5);
        }
        else {
            static3.setScore(tempScore1.getScore());
            scoreOverall1.setScore(scoreOverall1.getScore()+tempScore1.getScore());
        }
        scoreOverallRepository.save(scoreOverall1);
        static3Repository.save(static3);
        tempScoreRepository.deleteById(submitStaticDTO.getUserId());
    }
}
