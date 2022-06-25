package com.mannal.server.repository.Impl;

import com.mannal.server.entity.survey.QSurveyCategoryEntity;
import com.mannal.server.entity.survey.SurveyCategoryEntity;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
@EnableAutoConfiguration(exclude = {LiquibaseAutoConfiguration.class})
//@Import(TestConfig.class)
class SurveyCategoryEntityRepositoryImplTest {
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
        JPAQuery<SurveyCategoryEntity> jpaQuery = new JPAQuery<>(em);
        QSurveyCategoryEntity qSurveyCategory = QSurveyCategoryEntity.surveyCategoryEntity;
        List<SurveyCategoryEntity> surveyCategories = jpaQuery.select(qSurveyCategory)
                .from(qSurveyCategory)
                .fetch();

        assertThat(surveyCategories.size()).isEqualTo(4);
    }
}
