package com.mannal.server.repository.Impl;


import com.mannal.server.entity.survey.SurveyCategoryEntity;
import com.mannal.server.entity.survey.SurveyEntity;
import com.mannal.server.repository.SurveyRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

import static com.mannal.server.entity.survey.QSurveyEntity.surveyEntity;

@Repository("surveyRepository")
public class SurveyRepositoryImpl extends QuerydslRepositorySupport implements SurveyRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public SurveyRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(SurveyCategoryEntity.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public SurveyEntity findSurvey(UUID categoryId) {
        return jpaQueryFactory.select(surveyEntity)
                .from(surveyEntity)
                .where(surveyEntity.id.eq(categoryId))
                .fetch()
                .get(0);
    }

    // 페이징 참고
//    @Override
//    public PageImpl<SurveyCategory> findAllSurveyCategoryPage() {
//        JPQLQuery<SurveyCategory> jpaQuery = from(surveyCategory)
//                .select(surveyCategory);
//
//        JPQLQuery<SurveyCategory> query = getQuerydsl().applyPagination(pag)
//    }
}
