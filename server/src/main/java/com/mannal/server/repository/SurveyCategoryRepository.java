package com.mannal.server.repository;

import com.mannal.server.entity.survey.SurveyCategoryEntity;

import java.util.List;

public interface SurveyCategoryRepository {
    List<SurveyCategoryEntity> findAllSurveyCategory();
}
