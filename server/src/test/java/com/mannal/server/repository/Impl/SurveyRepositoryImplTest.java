package com.mannal.server.repository.Impl;

import com.mannal.server.entity.survey.*;
import com.querydsl.jpa.impl.JPAQuery;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
@EnableAutoConfiguration(exclude = {LiquibaseAutoConfiguration.class})
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

    private List<SurveyItemResultEntity> getSurveryResults(UUID requestId, SurveyItemEntity si) {
        SurveyItemResultEntity sr1 = SurveyItemResultEntity.builder()
//                .id(UUID.randomUUID())
                .surveyItemId(si.getId())
                .requestId(requestId)
                .type("undefined")
                .totalScore(10)
                .regDt(LocalDateTime.now())
                .editDt(LocalDateTime.now())
                .build();

        SurveyItemResultEntity sr2 = SurveyItemResultEntity.builder()
//                .id(UUID.randomUUID())
                .surveyItemId(UUID.randomUUID())
                .requestId(requestId)
                .type("undefined")
                .totalScore(10)
                .regDt(LocalDateTime.now())
                .editDt(LocalDateTime.now())
                .build();

        SurveyItemResultEntity sr3 = SurveyItemResultEntity.builder()
//                .id(UUID.randomUUID())
                .surveyItemId(UUID.randomUUID())
                .requestId(requestId)
                .type("undefined")
                .totalScore(10)
                .regDt(LocalDateTime.now())
                .editDt(LocalDateTime.now())
                .build();

        List<SurveyItemResultEntity> results = new ArrayList<>();
        results.add(sr1);
        results.add(sr2);
        results.add(sr3);

        return results;
    }

}