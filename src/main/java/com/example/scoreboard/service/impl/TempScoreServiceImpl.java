package com.example.scoreboard.service.impl;

import com.example.scoreboard.document.TempScore;
import com.example.scoreboard.dto.ValidationDTO;
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
    public void updateScore(ValidationDTO validationdto, int marks) {
        Optional<TempScore> tempScore1;

        tempScore1 = tempScoreRepository.findById(validationdto.getUserId());
        if (tempScore1.isPresent()) {
            TempScore tempScore = new TempScore();
            System.out.println(tempScore1);
            BeanUtils.copyProperties(tempScore1.get(), tempScore);
            tempScore.setScore(tempScore.getScore() + marks);
            System.out.println(tempScore);
            tempScoreRepository.save(tempScore);
        } else {
            TempScore tempScore = new TempScore();
            tempScore.setUserId(validationdto.getUserId());
            tempScore.setQuizId(validationdto.getQuizId());
            tempScore.setScore(marks);
            tempScoreRepository.save(tempScore);
        }
    }
}
