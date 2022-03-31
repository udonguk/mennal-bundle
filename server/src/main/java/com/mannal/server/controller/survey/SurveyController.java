package com.mannal.server.controller.survey;

import com.mannal.server.dto.SurveyResultDto;
import com.mannal.server.entity.survey.SurveyEntity;
import com.mannal.server.entity.survey.SurveyItemOptionResultEntity;
import com.mannal.server.entity.survey.SurveyItemResultEntity;
import com.mannal.server.service.survey.SurveyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

    @PostMapping("/option")
    @ResponseBody
    public ResponseEntity<SurveyResultDto> sendSurveyOptionResult(
            @RequestBody List<SurveyItemOptionResultEntity> surveyItemOptionResultEntityList
    ){

        UUID requestId = UUID.randomUUID();
        LocalDateTime currentTime = LocalDateTime.now();

        for (SurveyItemOptionResultEntity surveyItemOptionResult : surveyItemOptionResultEntityList) {
            setSurveyOptionResultDefault(requestId, currentTime, surveyItemOptionResult);
        }

        surveyService.saveOptionResult(surveyItemOptionResultEntityList);
        return ResponseEntity.ok(surveyService.getOptionResult(requestId));
    }

    private void setSurveyResultDefault(UUID requestId, LocalDateTime currentTime, SurveyItemResultEntity surveyResult) {
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
