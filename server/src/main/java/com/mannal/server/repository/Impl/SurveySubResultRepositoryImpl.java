package com.mannal.server.repository.Impl;


import com.mannal.server.entity.survey.SurveyCategoryEntity;
import com.mannal.server.entity.survey.SurveySubResultEntity;
import com.mannal.server.repository.SurveySubResultRepository;
import com.querydsl.core.types.Ops;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.UUID;

import static com.mannal.server.entity.survey.QSurveySubResultEntity.surveySubResultEntity;

@Repository("surveySubResultRepository")
public class SurveySubResultRepositoryImpl extends QuerydslRepositorySupport implements SurveySubResultRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public SurveySubResultRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(SurveyCategoryEntity.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }


    //final int constant = 10;
    //final QMyTable m = QMyTable.myTable;
    //final BooleanExpression operation = Expressions.booleanOperation(Ops.BETWEEN,
    //        Expressions.constant(constant), m.col1, m.col2);
    //
    //// This yields:
    //// SELECT ID, COL1, COL2 FROM MYTABLE WHERE (? BETWEEN COL1 AND COL2)
    //// bind => [10]
    //final MyTable actual = new JPAQuery(em).from(m).where(operation).uniqueResult(m);

    @Override
    public SurveySubResultEntity get(UUID surveySubCategoryId, Integer totalScore) {
        return jpaQueryFactory.select(surveySubResultEntity)
                .from(surveySubResultEntity)
                .where(Expressions.booleanOperation(
                        Ops.BETWEEN,
                        Expressions.constant(totalScore),
                        surveySubResultEntity.minScore,
                        surveySubResultEntity.maxScore))
                .where(surveySubResultEntity.surveySubCategoryId.eq(surveySubCategoryId))
                .fetchOne()
                ;
    }
}
