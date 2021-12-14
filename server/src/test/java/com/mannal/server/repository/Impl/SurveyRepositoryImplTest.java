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
import java.time.LocalDate;
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

    @Test
    void saveResultTest() {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        UUID requestId = UUID.randomUUID();

        SurveyEntity sc = SurveyEntity.builder()
                .title("테스트 카테고리")
                .code("01")
                .useYn("Y")
                .build();

        em.persist(sc);

        SurveySubCategoryEntity ss = SurveySubCategoryEntity.builder()
                .title("테스트 서브")
                .surveyEntity(sc)
                .code("01")
                .build();

        em.persist(ss);

        SurveyItemEntity si = SurveyItemEntity.builder()
                .title("테스트 아이템")
                .surveySubCategoryEntity(ss)
                .orderNum(0)
                .useYn("Y")
                .build();
        em.persist(si);

        List<SurveyResultEntity> surveyResultEntityList = getSurveryResults(requestId, si);
        for (SurveyResultEntity resultEntity : surveyResultEntityList) {
//            resultEntity.setSurveyItemEntity(si);
//            resultEntity.setSurveyItemId(si.getId());
            em.persist(resultEntity);
        }

        em.flush();
        JPAQuery<SurveyEntity> jpaQuery = new JPAQuery<>(em);
        QSurveyResultEntity surveyResultEntity = QSurveyResultEntity.surveyResultEntity;


        List<SurveyResultEntity> savedResult = jpaQuery.select(surveyResultEntity)
                .from(surveyResultEntity)
                .where(surveyResultEntity.requestId.eq(requestId))
                .fetch();

        assertThat(savedResult.size()).isEqualTo(3);

    }

    private List<SurveyResultEntity> getSurveryResults(UUID requestId, SurveyItemEntity si) {
        SurveyResultEntity sr1 = SurveyResultEntity.builder()
//                .id(UUID.randomUUID())
                .surveyItemId(si.getId())
                .requestId(requestId)
                .type("undefined")
                .totalScore(10)
                .regDt(LocalDateTime.now())
                .editDt(LocalDateTime.now())
                .build();

        SurveyResultEntity sr2 = SurveyResultEntity.builder()
//                .id(UUID.randomUUID())
                .surveyItemId(UUID.randomUUID())
                .requestId(requestId)
                .type("undefined")
                .totalScore(10)
                .regDt(LocalDateTime.now())
                .editDt(LocalDateTime.now())
                .build();

        SurveyResultEntity sr3 = SurveyResultEntity.builder()
//                .id(UUID.randomUUID())
                .surveyItemId(UUID.randomUUID())
                .requestId(requestId)
                .type("undefined")
                .totalScore(10)
                .regDt(LocalDateTime.now())
                .editDt(LocalDateTime.now())
                .build();

        List<SurveyResultEntity> results = new ArrayList<>();
        results.add(sr1);
        results.add(sr2);
        results.add(sr3);

        return results;
    }

}