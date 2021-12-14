package com.mannal.server.repository;

import com.mannal.server.entity.survey.SurveyEntity;
import com.mannal.server.entity.survey.SurveyResultEntity;

import java.util.List;

public interface SurveyResultRepository {
    SurveyEntity save(List<SurveyResultEntity> surveyResultEntities);
}
