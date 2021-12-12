package com.mannal.server.repository;

import com.mannal.server.entity.survey.SurveyCategory;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SurveyCategoryRepository {
    List<SurveyCategory> findAllSurveyCategory();
}
