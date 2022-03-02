package com.mannal.server.repository;

import com.mannal.server.entity.survey.SurveyEntity;
import com.mannal.server.entity.survey.SurveyItemOptionResultEntity;
import com.mannal.server.entity.survey.SurveyResultEntity;

import java.util.List;
import java.util.UUID;

public interface SurveyRepository {
    SurveyEntity findSurvey(UUID categoryId);

    void saves(List<SurveyResultEntity> surveyResultEntityList);

    void savesOptionResult(List<SurveyItemOptionResultEntity> surveyItemOptionResultEntityList);

    List<SurveyResultEntity> findSurveyResult(UUID requestId);

    List<SurveyItemOptionResultEntity> findSurveyOptionResult(UUID requestId);

    SurveyEntity findSurveyByResult(UUID requestId);
}
