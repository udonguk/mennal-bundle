package com.mannal.server.repository;

import com.mannal.server.entity.survey.SurveyEntity;
import com.mannal.server.entity.survey.SurveyItemResultEntity;

import java.util.List;
import java.util.UUID;

public interface SurveyItemResultRepository {
    SurveyEntity save(List<SurveyItemResultEntity> surveyResultEntities);
}
