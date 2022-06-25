package com.mannal.server.repository.Impl;

import com.mannal.server.dto.StatisticDto;
import com.mannal.server.entity.survey.SurveyCategoryEntity;
import com.mannal.server.entity.survey.SurveyItemOptionResultEntity;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;

import java.util.List;

import static com.mannal.server.entity.survey.QSurveyEntity.surveyEntity;
import static com.mannal.server.entity.survey.QSurveyItemEntity.surveyItemEntity;
import static com.mannal.server.entity.survey.QSurveyItemOptionEntity.surveyItemOptionEntity;
import static com.mannal.server.entity.survey.QSurveyItemOptionResultEntity.surveyItemOptionResultEntity;
import static com.mannal.server.entity.survey.QSurveySubCategoryEntity.surveySubCategoryEntity;

import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
@EnableAutoConfiguration(exclude = {LiquibaseAutoConfiguration.class})
class SurveyItemOptionResultRepositoryImplTest {

    @PersistenceUnit
    private EntityManagerFactory jpaQueryFactory;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void get() {
        EntityManager em = jpaQueryFactory.createEntityManager();
        JPAQuery<SurveyItemOptionResultEntity> jpaQuery = new JPAQuery<>(em);
        List<StatisticDto> result = jpaQuery
                .select(Projections.constructor(StatisticDto.class
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
                .on(surveyEntity.code.eq("01"))
                .groupBy(surveyItemOptionEntity.optionType)
                .fetch();

        assertThat(result.size()).isGreaterThan(0);
    }

}
