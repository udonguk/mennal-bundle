package com.mannal.server.repository;

import com.mannal.server.entity.survey.SurveyEntity;

import java.util.List;
import java.util.UUID;

public interface SurveyRepository {
    List<SurveyEntity> findSurvey(UUID categoryId);
}
