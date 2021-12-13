package com.mannal.server.service.survey.impl;

import com.mannal.server.entity.survey.SurveyCategoryEntity;
import com.mannal.server.entity.survey.SurveyEntity;
import com.mannal.server.repository.SurveyCategoryRepository;
import com.mannal.server.repository.SurveyRepository;
import com.mannal.server.service.survey.SurveyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("surveyService")
public class SurveyServiceImpl implements SurveyService {

    private final SurveyCategoryRepository surveyCategoryRepository;

    private final SurveyRepository surveyRepository;

    public SurveyServiceImpl(SurveyCategoryRepository surveyCategoryRepository, SurveyRepository surveyRepository) {
        this.surveyCategoryRepository = surveyCategoryRepository;
        this.surveyRepository = surveyRepository;
    }

    @Override
    public List<SurveyCategoryEntity> getAllSurveys() {
        return surveyCategoryRepository.findAllSurveyCategory();
    }

    @Override
    public SurveyEntity findSurvey(UUID categoryId) {
        return surveyRepository.findSurvey(categoryId);
    }
}
