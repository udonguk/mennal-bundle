package com.mannal.server.repository.Impl;


import com.mannal.server.entity.survey.SurveyCategoryEntity;
import com.mannal.server.repository.SurveyCategoryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.mannal.server.entity.survey.QSurveyCategoryEntity.surveyCategoryEntity;

@Repository("surveyCategory")
public class SurveyCategoryImpl extends QuerydslRepositorySupport implements SurveyCategoryRepository{

    private final JPAQueryFactory jpaQueryFactory;

    public SurveyCategoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(SurveyCategoryEntity.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<SurveyCategoryEntity> findAllSurveyCategory() {
        return jpaQueryFactory.select(surveyCategoryEntity)
                .from(surveyCategoryEntity)
                .fetch();
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
