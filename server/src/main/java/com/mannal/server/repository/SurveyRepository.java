package com.mannal.server.repository;

import com.mannal.server.entity.survey.SurveyEntity;
import com.mannal.server.entity.survey.SurveyItemOptionResultEntity;
import com.mannal.server.entity.survey.SurveyItemResultEntity;

import java.util.List;
import java.util.UUID;

public interface SurveyRepository {
    SurveyEntity findSurvey(UUID categoryId);

    void saves(List<SurveyItemResultEntity> surveyItemResultEntityList);

    void savesOptionResult(List<SurveyItemOptionResultEntity> surveyItemOptionResultEntityList);

    List<SurveyItemResultEntity> findSurveyItemResult(UUID requestId);

    List<SurveyItemOptionResultEntity> findSurveyOptionResult(UUID requestId);

    SurveyEntity findSurveyByResult(UUID requestId);
}
