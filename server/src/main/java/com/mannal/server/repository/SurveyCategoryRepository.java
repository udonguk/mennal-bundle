package com.mannal.server.repository;

import com.mannal.server.entity.survey.SurveyCategory;

import java.util.List;

public interface SurveyCategoryRepository {
    List<SurveyCategory> findAllSurveyCategory();
}
