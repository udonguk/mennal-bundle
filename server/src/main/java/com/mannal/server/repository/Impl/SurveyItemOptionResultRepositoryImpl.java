package com.mannal.server.repository.Impl;

import com.mannal.server.entity.survey.SurveyItemOptionResultEntity;
import com.mannal.server.repository.SurveyItemOptionResultRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.mannal.server.entity.survey.QSurveyItemEntity.surveyItemEntity;
import static com.mannal.server.entity.survey.QSurveyItemOptionEntity.surveyItemOptionEntity;
import static com.mannal.server.entity.survey.QSurveyItemOptionResultEntity.surveyItemOptionResultEntity;
import static com.mannal.server.entity.survey.QSurveySubCategoryEntity.surveySubCategoryEntity;
import static com.mannal.server.entity.survey.QSurveyEntity.surveyEntity;

@Repository("surveyItemOptionResult")
public class SurveyItemOptionResultRepositoryImpl extends QuerydslRepositorySupport implements SurveyItemOptionResultRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public SurveyItemOptionResultRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(SurveyItemOptionResultEntity.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<SurveyItemOptionResultEntity> get(String categoryCode) {
        return jpaQueryFactory.select(surveyItemOptionResultEntity)
                .from(surveyItemOptionResultEntity, surveyItemOptionEntity)
                .innerJoin(surveyItemOptionResultEntity.surveyItemOptionEntity, surveyItemOptionEntity)
                .innerJoin(surveyItemOptionEntity.surveyItemEntity, surveyItemEntity)
                .innerJoin(surveyItemEntity.surveySubCategoryEntity, surveySubCategoryEntity)
                .innerJoin(surveySubCategoryEntity.surveyEntity, surveyEntity)
                .fetch();
    }
}
