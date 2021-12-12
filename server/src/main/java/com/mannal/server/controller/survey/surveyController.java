package com.mannal.server.controller.survey;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mannal.server.entity.survey.SurveyEntity;
import com.mannal.server.service.survey.SurveyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/survey/")
public class surveyController {

    private final SurveyService surveyService;

    public surveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }


    @GetMapping("/{categoryId}")
    @ResponseBody
    public ResponseEntity<List<SurveyEntity>> findSurvey(@PathVariable("categoryId") String categoryId){
        return ResponseEntity.ok(surveyService.findSurvey(UUID.fromString(categoryId)));
    }
}
