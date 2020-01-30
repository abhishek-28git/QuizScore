package com.example.scoreboard.service.impl;

import com.example.scoreboard.document.ScoreOverall;
import com.example.scoreboard.document.Static4;
import com.example.scoreboard.document.TempScore;
import com.example.scoreboard.dto.SubmitStaticDTO;
import com.example.scoreboard.repository.ScoreOverallRepository;
import com.example.scoreboard.repository.Static4Repository;
import com.example.scoreboard.repository.TempScoreRepository;
import com.example.scoreboard.service.Static4Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Static4ServiceImpl implements Static4Service {

    @Autowired
    Static4Repository static4Repository;

    @Autowired
    ScoreOverallRepository scoreOverallRepository;

    @Autowired
    TempScoreRepository tempScoreRepository;

    @Override
    public Static4 findStatic(String userId) {
        Optional<Static4> static4;
        static4 = static4Repository.findById(userId);
        if(static4.isPresent()){
            Static4 staticx = new Static4();
            BeanUtils.copyProperties(static4.get(),staticx);
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
        Static4 static4 = new Static4();
        static4.setUserId(submitStaticDTO.getUserId());
        Optional<ScoreOverall> scoreOverall;
        scoreOverall=scoreOverallRepository.findById(submitStaticDTO.getUserId());
        ScoreOverall scoreOverall1 = new ScoreOverall();
        BeanUtils.copyProperties(scoreOverall.get(), scoreOverall1 );
        if(!skip){
            static4.setScore(tempScore1.getScore()+5);
            scoreOverall1.setScore(scoreOverall1.getScore()+tempScore1.getScore()+5);
        }
        else {
            static4.setScore(tempScore1.getScore());
            scoreOverall1.setScore(scoreOverall1.getScore()+tempScore1.getScore());
        }
        scoreOverallRepository.save(scoreOverall1);
        static4Repository.save(static4);
        tempScoreRepository.deleteById(submitStaticDTO.getUserId());
    }
}
