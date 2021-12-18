package com.mannal.server.repository;

import com.mannal.server.entity.survey.SurveySubResultEntity;

import java.util.UUID;

public interface SurveySubResultRepository {
    SurveySubResultEntity get(UUID surveySubCategoryId, Integer totalScore);
}
