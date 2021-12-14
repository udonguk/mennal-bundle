package com.mannal.server.service.survey.impl;

import com.mannal.server.dto.SurveyResultDto;
import com.mannal.server.entity.survey.*;
import com.mannal.server.exam.D02.ExamD02;
import com.mannal.server.repository.SurveyCategoryRepository;
import com.mannal.server.repository.SurveyRepository;
import com.mannal.server.service.survey.SurveyService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public void saveResult(List<SurveyResultEntity> surveyResultEntityList) {
        surveyRepository.saves(surveyResultEntityList);
    }

    @Override
    public SurveyResultDto getResult(UUID requestId) {
        SurveyResultDto result = null;
        List<SurveyResultEntity> surveyResultEntities = surveyRepository.findSurveyResult(requestId);
        SurveyEntity surveyEntity = surveyRepository.findSurveyByResult(requestId);

        setResultToSurveyEntity(surveyResultEntities, surveyEntity);
        ExamD02 examD02 = new ExamD02();
        switch (surveyEntity.getCode()){
            case "02":
                result = examD02.getResult(surveyEntity);
                break;
            case "04":
                // todo
                result = examD02.getResult(surveyEntity);
                break;
            default:
                break;
        }

        return result;
    }

    private void setResultToSurveyEntity(List<SurveyResultEntity> surveyResultEntities, SurveyEntity surveyEntity) {
        for (SurveySubCategoryEntity subCategory : surveyEntity.getSurveySubCategories()) {
            for (SurveyItemEntity item : subCategory.getSurveyItemEntities()) {
                if (null == item.getSurveyResultEntities()) {
                    item.setSurveyResultEntities(new ArrayList<>());
                }
                List<SurveyResultEntity> results = item.getSurveyResultEntities();

                for(SurveyResultEntity surveyResultEntity: surveyResultEntities){
                    if(item.getId().equals(surveyResultEntity.getSurveyItemId())){
                        results.add(surveyResultEntity);
                        break;
                    }
                }
            }
        }
    }
}
