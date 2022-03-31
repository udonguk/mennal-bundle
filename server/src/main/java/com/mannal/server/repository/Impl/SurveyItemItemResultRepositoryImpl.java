package com.mannal.server.repository.Impl;


import com.mannal.server.entity.survey.SurveyCategoryEntity;
import com.mannal.server.entity.survey.SurveyEntity;
import com.mannal.server.entity.survey.SurveyItemResultEntity;
import com.mannal.server.repository.SurveyItemResultRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("surveyItemResultRepository")
public class SurveyItemItemResultRepositoryImpl
        extends QuerydslRepositorySupport
        implements SurveyItemResultRepository {

    public SurveyItemItemResultRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(SurveyCategoryEntity.class);
    }

    @Override
    public SurveyEntity save(List<SurveyItemResultEntity> surveyResultEntities) {
        return null;
    }
}
