package com.mannal.server.repository.Impl;

import com.mannal.server.entity.survey.SurveyCategoryEntity;
import com.querydsl.core.types.Ops;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.UUID;

import static com.mannal.server.entity.survey.QSurveySubResultEntity.surveySubResultEntity;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@EnableAutoConfiguration(exclude = {LiquibaseAutoConfiguration.class})
class SurveySubResultRepositoryImplTest {

    @Test
    void get() {
//        EntityManager em = factory.createEntityManager();
//        JPAQuery<SurveyCategoryEntity> jpaQuery = new JPAQuery<>(em);
//
//        return jpaQuery.select(surveySubResultEntity)
//                .from(surveySubResultEntity)
//                .where(Expressions.booleanOperation(
//                        Ops.BETWEEN,
//                        Expressions.constant(totalScore),
//                        surveySubResultEntity.minScore,
//                        surveySubResultEntity.maxScore))
//                .where(surveySubResultEntity.surveySubCategoryId.eq(UUID.randomUUID()))
//                .fetchOne()
//                ;
    }

}