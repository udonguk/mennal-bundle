package com.mannal.server.repository.Impl;

import com.mannal.server.entity.survey.SurveyCategoryEntity;
import com.mannal.server.entity.survey.SurveyItemOptionEntity;
import com.mannal.server.repository.SurveyItemOptionRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.UUID;

import static com.mannal.server.entity.survey.QSurveyItemOptionEntity.surveyItemOptionEntity;

@Repository("surveyItemOptionRepository")
public class SurveyItemOptionRepositoryImpl extends QuerydslRepositorySupport implements SurveyItemOptionRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public SurveyItemOptionRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(SurveyCategoryEntity.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public SurveyItemOptionEntity get(UUID id) {
        return jpaQueryFactory.select(surveyItemOptionEntity)
                .from(surveyItemOptionEntity)
                .where(surveyItemOptionEntity.id.eq(id))
                .fetchOne();
    }
}
