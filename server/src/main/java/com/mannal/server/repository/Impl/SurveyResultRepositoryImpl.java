package com.mannal.server.repository.Impl;


import com.mannal.server.entity.survey.SurveyCategoryEntity;
import com.mannal.server.entity.survey.SurveyEntity;
import com.mannal.server.entity.survey.SurveyResultEntity;
import com.mannal.server.repository.SurveyResultRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("surveyResultRepository")
public class SurveyResultRepositoryImpl extends QuerydslRepositorySupport
        implements SurveyResultRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public SurveyResultRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(SurveyCategoryEntity.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public SurveyEntity save(List<SurveyResultEntity> surveyResultEntities) {
        return null;
    }
}
