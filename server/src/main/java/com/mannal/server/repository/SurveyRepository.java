package com.mannal.server.repository;

import com.mannal.server.entity.survey.SurveyEntity;
import com.mannal.server.entity.survey.SurveyResultEntity;

import java.util.List;
import java.util.UUID;

public interface SurveyRepository {
    SurveyEntity findSurvey(UUID categoryId);

    void saves(List<SurveyResultEntity> surveyResultEntityList);

    List<SurveyResultEntity> findSurveyResult(UUID requestId);

    SurveyEntity findSurveyByResult(UUID requestId);
}
