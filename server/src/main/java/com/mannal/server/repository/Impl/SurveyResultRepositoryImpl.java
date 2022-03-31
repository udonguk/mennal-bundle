package com.mannal.server.repository.Impl;


import com.mannal.server.entity.survey.SurveyCategoryEntity;
import com.mannal.server.entity.survey.SurveyResultEntity;
import com.mannal.server.repository.SurveyResultRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;

import static com.mannal.server.entity.survey.QSurveyResultEntity.surveyResultEntity;

@Repository("surveyResultRepository")
public class SurveyResultRepositoryImpl
        extends QuerydslRepositorySupport
        implements SurveyResultRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @PersistenceContext
    private EntityManager em;

    public SurveyResultRepositoryImpl(JPAQueryFactory jpaQueryFactory, EntityManager em) {
        super(SurveyCategoryEntity.class);
        this.jpaQueryFactory = jpaQueryFactory;
        this.em = em;
    }

    @Override
    public List<SurveyResultEntity> findBySurveyCategoryId(UUID surveyCategoryId) {
        return jpaQueryFactory.select(surveyResultEntity)
                .from(surveyResultEntity)
                .where(surveyResultEntity.surveyCategoryId.eq(surveyCategoryId))
                .fetch();
    }
}
