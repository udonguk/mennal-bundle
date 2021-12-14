package com.mannal.server.repository.Impl;


import com.mannal.server.entity.survey.SurveyCategoryEntity;
import com.mannal.server.entity.survey.SurveyEntity;
import com.mannal.server.entity.survey.SurveyItemEntity;
import com.mannal.server.entity.survey.SurveyResultEntity;
import com.mannal.server.repository.SurveyRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;


import static com.mannal.server.entity.survey.QSurveyEntity.surveyEntity;
import static com.mannal.server.entity.survey.QSurveySubCategoryEntity.surveySubCategoryEntity;
import static com.mannal.server.entity.survey.QSurveyItemEntity.surveyItemEntity;
import static com.mannal.server.entity.survey.QSurveyResultEntity.surveyResultEntity;

@Repository("surveyRepository")
public class SurveyRepositoryImpl extends QuerydslRepositorySupport implements SurveyRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @PersistenceContext
    private EntityManager em;

    public SurveyRepositoryImpl(JPAQueryFactory jpaQueryFactory, EntityManager em) {
        super(SurveyCategoryEntity.class);
        this.jpaQueryFactory = jpaQueryFactory;
        this.em = em;
    }

    @Override
    public SurveyEntity findSurvey(UUID categoryId) {
        return jpaQueryFactory.select(surveyEntity)
                .from(surveyEntity)
                .where(surveyEntity.id.eq(categoryId))
                .fetch()
                .get(0);
    }

    @Override
    @Transactional
    public void saves(List<SurveyResultEntity> surveyResultEntityList) {
        for (SurveyResultEntity resultEntity : surveyResultEntityList) {
//            SurveyItemEntity surveyItem = jpaQueryFactory.select(surveyItemEntity)
//                    .from(surveyItemEntity)
//                    .where(surveyItemEntity.id.eq(resultEntity.getSurveyItemId()))
//                    .fetch().get(0);
//            resultEntity.setSurveyItemId(surveyItem.getId());
//            resultEntity.setSurveyItemEntity(surveyItem);
            em.persist(resultEntity);
        }
    }

    @Override
    public List<SurveyResultEntity> findSurveyResult(UUID requestId) {
        return jpaQueryFactory.select(surveyResultEntity)
                .from(surveyResultEntity)
                .where(surveyResultEntity.requestId.eq(requestId))
                .fetch()
                ;
    }

    @Override
    public SurveyEntity findSurveyByResult(UUID requestId) {
        return jpaQueryFactory.select(surveyEntity)
                .from(surveyEntity)
                .join(surveySubCategoryEntity)
                    .on(surveySubCategoryEntity.surveyEntity.eq(surveyEntity))
                .join(surveyItemEntity)
                    .on(surveyItemEntity.surveySubCategoryEntity.eq(surveySubCategoryEntity))
                .join(surveyResultEntity)
                    .on(surveyResultEntity.surveyItemId.eq(surveyItemEntity.id))
                .where(surveyResultEntity.requestId.eq(requestId))
                .fetch()
                .get(0)
                ;
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
