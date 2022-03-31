package com.mannal.server.service.survey;

import com.mannal.server.dto.SurveyResultDto;
import com.mannal.server.entity.survey.SurveyCategoryEntity;
import com.mannal.server.entity.survey.SurveyEntity;
import com.mannal.server.entity.survey.SurveyItemOptionResultEntity;
import com.mannal.server.entity.survey.SurveyItemResultEntity;

import java.util.List;
import java.util.UUID;

public interface SurveyService {
    List<SurveyCategoryEntity> getAllSurveys();

    SurveyEntity findSurvey(UUID categoryId);

    void saveOptionResult(List<SurveyItemOptionResultEntity> surveyItemOptionResultEntityList);

    SurveyResultDto getOptionResult(UUID requestId);
}