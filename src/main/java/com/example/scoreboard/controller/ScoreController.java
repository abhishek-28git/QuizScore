package com.example.scoreboard.controller;

import com.example.scoreboard.document.*;
import com.example.scoreboard.dto.*;
import com.example.scoreboard.response.APIResponse;
import com.example.scoreboard.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RestController
@RequestMapping("/score")
public class ScoreController {

    @Autowired
    ScoreDynamicService scoreDynamicService;

    @Autowired
    Static1Service static1Service;

    @Autowired
    Static2Service static2Service;

    @Autowired
    Static3Service static3Service;

    @Autowired
    Static4Service static4Service;

    @Autowired
    Static5Service static5Service;

    @Autowired
    ScoreOverallService scoreOverallService;

    @Autowired
    TempScoreService tempScoreService;


    @GetMapping("/getindividualoverall/{userId}")
    public ResponseEntity<APIResponse<String>> getIndividualOverall(@PathVariable("userId") String userId) {
            ScoreOverall scoreOverall = scoreOverallService.findOverallById(userId);
            APIResponse response = new APIResponse();
            response.setStatus(true);
            response.setMessage("good");
            response.setData(scoreOverall);
            return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("/getindividualstatic/{userId}/{quizId}")
    public ResponseEntity<APIResponse<String >> getStaticOverall(@PathVariable("userId") String userId , @PathVariable("quizId") int id) {
        APIResponse response = new APIResponse();
            if(id==1)
            {
                Static1 static1 = static1Service.findStatic(userId);
                response.setStatus(true);
                response.setMessage("Static1");
                response.setData(static1);
            }
            else if(id==2)
            {
                Static2 static2 = static2Service.findStatic(userId);
                response.setStatus(true);
                response.setMessage("Static2");
                response.setData(static2);

            }
            else if(id==3)
            {
                Static3 static3 = static3Service.findStatic(userId);
                response.setStatus(true);
                response.setMessage("Static3");
                response.setData(static3);
            }
            else if(id==4)
            {
                Static4 static4 = static4Service.findStatic(userId);
                response.setStatus(true);
                response.setMessage("Static4");
                response.setData(static4);
            }
            else
            {
                Static5 static5 = static5Service.findStatic(userId);
                response.setStatus(true);
                response.setMessage("Static5");
                response.setData(static5);

            }
        return null;
    }

    @PostMapping("/validateanswer")
    public ResponseEntity<APIResponse<String>> validateDynamic(@RequestBody ValidationDTO validationDTO){
        final String uri = "http://10.177.69.0:8082/search/get/";
        RestTemplate restTemplate = new RestTemplate();
        ParameterizedTypeReference<APIResponse<QuestionDTO>> parameterizedTypeReference = new ParameterizedTypeReference<APIResponse<QuestionDTO>>(){};
        APIResponse<QuestionDTO> responseElastic = restTemplate.exchange(uri + validationDTO.getQuestionId(), HttpMethod.GET, null, parameterizedTypeReference).getBody();
        QuestionDTO questionDTO = responseElastic.getData();
        int marks=1;
        if(questionDTO.getDifficulty().equalsIgnoreCase("medium"))
            marks = 2;
        else if(questionDTO.getDifficulty().equalsIgnoreCase("difficult"))
            marks=3;


        if(validationDTO.getAnswer().equals(questionDTO.getAnswer())){
            tempScoreService.updateScore(validationDTO, marks);
            APIResponse response = new APIResponse();
            response.setStatus(true);
            response.setMessage("true");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else{
            APIResponse response = new APIResponse();
            response.setStatus(true);
            response.setMessage("false");
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
    }


    @PostMapping("/submitdynamic")
    public ResponseEntity<APIResponse<String>> submitDynamicQuiz(@RequestBody SubmitDynamicDTO submitDynamicDTO){
        APIResponse response = new APIResponse();
        scoreDynamicService.submitScore(submitDynamicDTO);
        return new ResponseEntity<>(response , HttpStatus.OK);
    }

    @PostMapping("/submitstatic/{id}/{skip}")
    public ResponseEntity<APIResponse<String>> submitStaticQuiz(@RequestBody SubmitStaticDTO submitStaticDTO , @PathVariable("id") int id , @PathVariable("skip") boolean skip){
        APIResponse response = new APIResponse();
        if(id==1)
        {
            static1Service.submitScore(submitStaticDTO , skip);
        }
        else if(id==2)
        {
            static2Service.submitScore(submitStaticDTO, skip);
        }
        else if(id==3)
        {
            static3Service.submitScore(submitStaticDTO , skip);
        }
        else if(id==4)
        {
            static4Service.submitScore(submitStaticDTO , skip);
        }
        else if(id==5)
            static5Service.submitScore(submitStaticDTO , skip);
        response.setStatus(true);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/overallleaderboard")
    public  ResponseEntity<APIResponse<String>> overallLeaderBoard(){
        APIResponse response = new APIResponse();
        List<ScoreOverall> leaderboard;
        leaderboard = scoreOverallService.findAll();
        response.setStatus(true);
        response.setMessage("leaderboard");
        response.setData(leaderboard);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @GetMapping()


}
