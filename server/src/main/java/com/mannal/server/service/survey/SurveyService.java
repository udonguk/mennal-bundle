package com.mannal.server.service.survey;

import com.mannal.server.entity.survey.SurveyCategory;
import com.mannal.server.entity.survey.SurveyEntity;

import java.util.List;
import java.util.UUID;

public interface SurveyService {
    List<SurveyCategory> getAllSurveys();

    List<SurveyEntity> findSurvey(UUID categoryId);
}