package com.mannal.server.repository.Impl;

import com.mannal.server.config.TestConfig;
import com.mannal.server.entity.survey.QSurveyEntity;
import com.mannal.server.entity.survey.SurveyEntity;
import com.querydsl.jpa.impl.JPAQuery;
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
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
@EnableAutoConfiguration(exclude = {LiquibaseAutoConfiguration.class})
//@Import(TestConfig.class)
class SurveyRepositoryImplTest {

    @PersistenceUnit
    private EntityManagerFactory factory;

    @Test
    void findSurvey() {
        EntityManager em = factory.createEntityManager();
        JPAQuery<SurveyEntity> jpaQuery = new JPAQuery<>(em);
        QSurveyEntity qSurveyEntity = QSurveyEntity.surveyEntity;

        UUID categoryId = UUID.fromString("8e02fc1e-caab-447e-bcfb-426c254e16dc");

        List<SurveyEntity> surveyCategories = jpaQuery.select(qSurveyEntity)
                .from(qSurveyEntity)
                .where(qSurveyEntity.id.eq(categoryId))
                .fetch();

        assertThat(surveyCategories.get(0).getId()).isEqualTo(categoryId);
    }

}