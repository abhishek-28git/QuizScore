package com.example.scoreboard.controller;

import com.example.scoreboard.document.User;
import com.example.scoreboard.dto.*;
import com.example.scoreboard.response.APIResponse;
import com.example.scoreboard.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/score")
public class ScoreController {

    @Autowired
    ScoreDynamicService scoreDynamicService;

    @Autowired
    ScoreStaticService scoreStaticService;

    @Autowired
    ScoreOverallService scoreOverallService;

    @Autowired
    UserService userService;

    @Autowired
    TempScoreService tempScoreService;



    @PostMapping("/register")
    public ResponseEntity<APIResponse<String>> registerUser(@RequestBody UserDTO UserDTO)
    {
        User User = new User();
        BeanUtils.copyProperties(UserDTO, User);
        APIResponse response;
        response = userService.insert(User);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/getIndividualOverall/{userId}")
    public ResponseEntity<APIResponse<String>> getIndividualOverall(@PathVariable("userId") String userId) {
        if (userService.userExists(userId)) {
            APIResponse response = scoreOverallService.findOverallById(userId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            APIResponse response = new APIResponse();
            response.setMessage("No Such User");
            response.setStatus(200);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @GetMapping("/getIndividualStatic/{userId}")
    public ResponseEntity<APIResponse<String >> getStaticOverall(@PathVariable("userId") String userId) {
        if (userService.userExists(userId)) {
            APIResponse response = scoreStaticService.findStatic(userId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else
        {
            APIResponse response = new APIResponse();
            response.setMessage("No Such User");
            response.setStatus(200);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @PostMapping("/validateAnswer")
    public ResponseEntity<APIResponse<String>> validateDynamic(@RequestBody validationDTO validationDTO){

        if(true){
            tempScoreService.updateScore(validationDTO, marks);
            APIResponse response = new APIResponse();
            response.setStatus(1000);
            response.setMessage("true");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else{
            APIResponse response = new APIResponse();
            response.setStatus(1000);
            response.setMessage("false");
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
    }

    @PostMapping("/SubmitDynamic")
    public ResponseEntity<APIResponse<String>> submitDynamicQuiz(@RequestBody SubmitDynamicDTO submitDynamicDTO){
        APIResponse response = new APIResponse();
        scoreDynamicService.submitScore(submitDynamicDTO);
        return new ResponseEntity<>(response , HttpStatus.OK);
    }

    @PostMapping("/submitStatic/{id}")
    public ResponseEntity<APIResponse<String>> submitStaticQuiz(@RequestBody SubmitStaticDTO submitStaticDTO , @PathVariable("id") int id){
        APIResponse response = new APIResponse();
        scoreStaticService.submitScore(submitStaticDTO , id);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/overallLeaderboard")
    public  ResponseEntity<APIResponse<String>> overallLeaderBoard(){
        APIResponse response = new APIResponse();
        response = scoreOverallService.findAll();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }



}
