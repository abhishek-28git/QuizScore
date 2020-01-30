package com.example.scoreboard.service.impl;

import com.example.scoreboard.document.TempScore;
import com.example.scoreboard.dto.validationDTO;
import com.example.scoreboard.repository.TempScoreRepository;
import com.example.scoreboard.service.TempScoreService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TempScoreServiceImpl implements TempScoreService {

    @Autowired
    TempScoreRepository tempScoreRepository;

    @Override
    public void updateScore(validationDTO validationDTO, int marks) {
        Optional<TempScore> tempScore1;
        tempScore1=tempScoreRepository.findById(validationDTO.getUserId());
        if(tempScore1.isPresent())
        {
            TempScore tempScore = new TempScore();
            BeanUtils.copyProperties(tempScore1, tempScore);
            tempScore.setScore(tempScore.getScore()+marks);
            tempScoreRepository.save(tempScore);
        }
        else {
            TempScore tempScore = new TempScore();
            tempScore.setUserId(validationDTO.getUserId());
            tempScore.setQuizId(validationDTO.getQuizId());
            tempScore.setScore(marks);
            tempScoreRepository.save(tempScore);
        }
    }
}
