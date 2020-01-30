package com.example.scoreboard.service.impl;

import com.example.scoreboard.document.ScoreDynamic;
import com.example.scoreboard.document.ScoreOverall;
import com.example.scoreboard.document.TempScore;
import com.example.scoreboard.dto.SubmitDynamicDTO;
import com.example.scoreboard.repository.ScoreOverallRepository;
import com.example.scoreboard.repository.TempScoreRepository;
import com.example.scoreboard.service.ScoreDynamicService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.scoreboard.repository.ScoreDynamicRepository;

import java.util.Optional;

@Service
public class ScoreDynamicServiceImpl implements ScoreDynamicService {

    @Autowired
    ScoreDynamicRepository scoreDynamicRepository;

    @Autowired
    TempScoreRepository tempScoreRepository;

    @Autowired
    ScoreOverallRepository scoreOverallRepository;

    @Override
    public void submitScore(SubmitDynamicDTO submitDynamicDTO) {
        ScoreDynamic scoreDynamic = new ScoreDynamic();
        scoreDynamic.setUserId(submitDynamicDTO.getUserId());
        scoreDynamic.setQuizId(submitDynamicDTO.getQuizId());
        Optional<TempScore> tempScore;
        tempScore = tempScoreRepository.findById(submitDynamicDTO.getUserId());
        if(tempScore.isPresent()){
            TempScore tempScore1 = new TempScore();
            BeanUtils.copyProperties(tempScore, tempScore1);
            scoreDynamic.setScore(tempScore1.getScore());
            Optional<ScoreOverall> scoreOverall;
            scoreOverall=scoreOverallRepository.findById(submitDynamicDTO.getUserId());
            ScoreOverall scoreOverall1 = new ScoreOverall();
            BeanUtils.copyProperties(scoreOverall, scoreOverall1 );
            scoreOverall1.setScore(scoreOverall1.getScore()+tempScore1.getScore());
            scoreOverallRepository.save(scoreOverall1);
        }
        scoreDynamicRepository.save(scoreDynamic);
        tempScoreRepository.deleteById(submitDynamicDTO.getUserId());
    }
}
