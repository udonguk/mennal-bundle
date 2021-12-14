package com.mannal.server.service.survey;

import com.mannal.server.dto.SurveyResultDto;
import com.mannal.server.entity.survey.SurveyCategoryEntity;
import com.mannal.server.entity.survey.SurveyEntity;
import com.mannal.server.entity.survey.SurveyResultEntity;

import java.util.List;
import java.util.UUID;

public interface SurveyService {
    List<SurveyCategoryEntity> getAllSurveys();

    SurveyEntity findSurvey(UUID categoryId);

    void saveResult(List<SurveyResultEntity> surveyResultEntityList);

    SurveyResultDto getResult(UUID requestId);
}