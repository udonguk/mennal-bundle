package com.mannal.server.service.survey.impl;

import com.mannal.server.entity.survey.SurveyCategory;
import com.mannal.server.repository.SurveyCategoryRepository;
import com.mannal.server.service.survey.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("surveyService")
public class SurveyServiceImpl implements SurveyService {

    @Autowired
    SurveyCategoryRepository surveyCategoryRepository;

    @Override
    public List<SurveyCategory> getAllSurveys() {
        return surveyCategoryRepository.findAllSurveyCategory();
    }
}
