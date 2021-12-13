package com.mannal.server.service.survey;

import com.mannal.server.entity.survey.SurveyCategoryEntity;
import com.mannal.server.entity.survey.SurveyEntity;

import java.util.List;
import java.util.UUID;

public interface SurveyService {
    List<SurveyCategoryEntity> getAllSurveys();

    List<SurveyEntity> findSurvey(UUID categoryId);
}