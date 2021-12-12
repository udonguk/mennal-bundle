package com.mannal.server.repository.Impl;

import com.mannal.server.config.TestConfig;
import com.mannal.server.entity.survey.QSurveyCategory;
import com.mannal.server.entity.survey.SurveyCategory;
import com.querydsl.jpa.impl.JPAQuery;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@EnableAutoConfiguration(exclude = {LiquibaseAutoConfiguration.class})
@Import(TestConfig.class)
class SurveyCategoryRepositoryImplTest {
    @PersistenceUnit
    private EntityManagerFactory factory;

    @BeforeEach
    void setUp() {
//        EntityManager em = factory.createEntityManager();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAllSurveyCategory() {
        EntityManager em = factory.createEntityManager();
        JPAQuery<SurveyCategory> jpaQuery = new JPAQuery<>(em);
        QSurveyCategory qSurveyCategory = QSurveyCategory.surveyCategory;
        List<SurveyCategory> surveyCategories = jpaQuery.select(qSurveyCategory)
                .from(qSurveyCategory)
                .fetch();

        assertThat(surveyCategories.size()).isEqualTo(2);
    }
}