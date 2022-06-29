package com.mannal.server.repository.Impl;

import com.mannal.server.dto.StatisticDto;
import com.mannal.server.entity.survey.SurveyItemOptionResultEntity;
import com.mannal.server.repository.SurveyItemOptionResultRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
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
    public List<StatisticDto> get(String categoryCode) {
        return jpaQueryFactory.select(Projections.constructor(StatisticDto.class
                        , surveyItemOptionEntity.optionType
                        , surveyItemOptionEntity.optionType.count()
                        , new CaseBuilder()
                                .when(surveyItemOptionResultEntity.checked.eq("Y"))
                                .then(1)
                                .otherwise(0).sum()
                        , new CaseBuilder()
                                .when(surveyItemOptionResultEntity.checked.eq("N"))
                                .then(1)
                                .otherwise(0).sum()
                ))
                .from(surveyItemOptionResultEntity)
                .innerJoin(surveyItemOptionResultEntity.surveyItemOptionEntity, surveyItemOptionEntity)
                .innerJoin(surveyItemOptionEntity.surveyItemEntity, surveyItemEntity)
                .innerJoin(surveyItemEntity.surveySubCategoryEntity, surveySubCategoryEntity)
                .innerJoin(surveySubCategoryEntity.surveyEntity, surveyEntity)
                .on(surveyEntity.code.eq(categoryCode))
                .groupBy(surveyItemOptionEntity.optionType)
                .fetch();
    }
}
