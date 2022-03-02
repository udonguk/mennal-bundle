package com.mannal.server.controller.survey;

import com.mannal.server.dto.SurveyResultDto;
import com.mannal.server.entity.survey.SurveyEntity;
import com.mannal.server.entity.survey.SurveyItemOptionResultEntity;
import com.mannal.server.entity.survey.SurveyResultEntity;
import com.mannal.server.service.survey.SurveyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/survey")
public class SurveyController {

    private final SurveyService surveyService;

    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }


    @GetMapping("/{categoryId}")
    @ResponseBody
    public ResponseEntity<SurveyEntity> findSurvey(@PathVariable("categoryId") String categoryId){
        return ResponseEntity.ok(surveyService.findSurvey(UUID.fromString(categoryId)));
    }

//    @PostMapping
//    public void add(@RequestBody List<UUID> userIds) {
//        adminService.save(userIds);
//    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<SurveyResultDto> sendSurvey(
            @RequestBody List<SurveyResultEntity> surveyResultEntityList
    ){

        UUID requestId = UUID.randomUUID();
        LocalDateTime currentTime = LocalDateTime.now();
        surveyResultEntityList.forEach(surveyResult -> {
            setSurveyResultDefault(requestId, currentTime, surveyResult);
        });

        surveyService.saveResult(surveyResultEntityList);
        return ResponseEntity.ok(surveyService.getResult(requestId));
    }

    @PostMapping("/option")
    @ResponseBody
    public ResponseEntity<SurveyResultDto> sendSurveyOptionResult(
            @RequestBody List<SurveyItemOptionResultEntity> surveyItemOptionResultEntityList
    ){

        UUID requestId = UUID.randomUUID();
        LocalDateTime currentTime = LocalDateTime.now();

        surveyItemOptionResultEntityList.forEach(surveyItemOptionResult -> {
            setSurveyOptionResultDefault(requestId, currentTime, surveyItemOptionResult);
        });

        surveyService.saveOptionResult(surveyItemOptionResultEntityList);
//        return ResponseEntity.ok(surveyService.getResult(requestId));
        return ResponseEntity.ok(surveyService.getOptionResult(requestId));
    }

    private void setSurveyResultDefault(UUID requestId, LocalDateTime currentTime, SurveyResultEntity surveyResult) {
        surveyResult.setRequestId(requestId);
        surveyResult.setUseYn("Y");
        surveyResult.setRegDt(currentTime);
        surveyResult.setEditDt(currentTime);
    }

    private void setSurveyOptionResultDefault(UUID requestId, LocalDateTime currentTime, SurveyItemOptionResultEntity surveyResult) {
        surveyResult.setChecked("true".equals(surveyResult.getChecked()) ? "Y" : "N");
        surveyResult.setRequestId(requestId);
        surveyResult.setUseYn("Y");
        surveyResult.setRegDt(currentTime);
        surveyResult.setEditDt(currentTime);
    }
}
