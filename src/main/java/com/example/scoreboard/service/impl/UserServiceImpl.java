package com.example.scoreboard.service.impl;

import com.example.scoreboard.document.ScoreDynamic;
import com.example.scoreboard.document.ScoreOverall;
import com.example.scoreboard.document.ScoreStatic;
import com.example.scoreboard.document.User;
import com.example.scoreboard.repository.ScoreDynamicRepository;
import com.example.scoreboard.repository.ScoreOverallRepository;
import com.example.scoreboard.repository.ScoreStaticRepository;
import com.example.scoreboard.repository.UserRepository;
import com.example.scoreboard.response.APIResponse;
import com.example.scoreboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ScoreDynamicRepository scoreDynamicRepository;

    @Autowired
    ScoreStaticRepository scoreStaticRepository;

    @Autowired
    ScoreOverallRepository scoreOverallRepository;

    @Override
    public APIResponse insert(User User) {

        boolean exists = userRepository.existsById(User.getUserId());
        if(exists)
        {
            APIResponse response = new APIResponse();
            response.setStatus(200);
            response.setMessage("User Already exists");
            return response;
        }
        else {
            ScoreDynamic dynamic = new ScoreDynamic();
            dynamic.setUserId(User.getUserId());
            scoreDynamicRepository.save(dynamic);

            ScoreOverall overall = new ScoreOverall();
            overall.setUserId(User.getUserId());
            scoreOverallRepository.save(overall);

            ScoreStatic scoreStatic = new ScoreStatic();
            scoreStatic.setUserId(User.getUserId());
            scoreStaticRepository.save(scoreStatic);

            APIResponse response = new APIResponse();
            response.setStatus(1000);
            response.setMessage("User Registered");
            return response;
        }
    }

    @Override
    public boolean userExists(String id) {
        return userRepository.existsById(id);
    }
}
