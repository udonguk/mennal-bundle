package com.mannal.server.repository;

import com.mannal.server.entity.survey.SurveyEntity;
import com.mannal.server.entity.survey.SurveyItemResultEntity;
import com.mannal.server.entity.survey.SurveyResultEntity;

import java.util.List;
import java.util.UUID;

public interface SurveyResultRepository {
    List<SurveyResultEntity> findBySurveyCategoryId(UUID surveyCategoryId);
}
