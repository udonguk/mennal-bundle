package com.mannal.server.service.survey;

import com.mannal.server.entity.survey.SurveyCategory;

import java.util.List;

public interface SurveyService {
    List<SurveyCategory> getAllSurveys();
}