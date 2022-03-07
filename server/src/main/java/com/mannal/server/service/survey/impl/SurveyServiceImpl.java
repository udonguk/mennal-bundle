package com.mannal.server.service.survey.impl;

import com.mannal.server.dto.FactionDto;
import com.mannal.server.dto.SurveyResultDto;
import com.mannal.server.entity.survey.*;
import com.mannal.server.repository.SurveyCategoryRepository;
import com.mannal.server.repository.SurveyItemOptionRepository;
import com.mannal.server.repository.SurveyRepository;
import com.mannal.server.repository.SurveySubResultRepository;
import com.mannal.server.service.survey.SurveyService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service("surveyService")
public class SurveyServiceImpl implements SurveyService {

    private final SurveyCategoryRepository surveyCategoryRepository;

    private final SurveyRepository surveyRepository;

    private final SurveyItemOptionRepository surveyItemOptionRepository;

    private final SurveySubResultRepository surveySubResultRepository;

    public SurveyServiceImpl(SurveyCategoryRepository surveyCategoryRepository
            , SurveyRepository surveyRepository
            , SurveyItemOptionRepository surveyItemOptionRepository, SurveySubResultRepository surveySubResultRepository
    ) {
        this.surveyCategoryRepository = surveyCategoryRepository;
        this.surveyRepository = surveyRepository;
        this.surveyItemOptionRepository = surveyItemOptionRepository;
        this.surveySubResultRepository = surveySubResultRepository;
    }

    @Override
    public List<SurveyCategoryEntity> getAllSurveys() {
        return surveyCategoryRepository.findAllSurveyCategory();
    }

    @Override
    public SurveyEntity findSurvey(UUID categoryId) {
        return surveyRepository.findSurvey(categoryId);
    }

    @Override
    public void saveResult(List<SurveyResultEntity> surveyResultEntityList) {
        surveyRepository.saves(surveyResultEntityList);
    }

    @Override
    public void saveOptionResult(List<SurveyItemOptionResultEntity> surveyItemOptionResultEntityList) {
        for (SurveyItemOptionResultEntity surveyItemOptionResult : surveyItemOptionResultEntityList) {
            surveyItemOptionResult.setSurveyItemOptionEntity(
                    surveyItemOptionRepository.get(surveyItemOptionResult.getSurveyItemOptionEntity().getId())
            );
        }
        surveyRepository.savesOptionResult(surveyItemOptionResultEntityList);
    }

    @Override
    public SurveyResultDto getResult(UUID requestId) {
        List<SurveyResultEntity> surveyResultEntities = surveyRepository.findSurveyResult(requestId);
        SurveyEntity surveyEntity = surveyRepository.findSurveyByResult(requestId);

        assembleResultToSurveyEntity(surveyResultEntities, surveyEntity);
        List<FactionDto> factionDtos = getFactions(surveyEntity);

        return SurveyResultDto.builder()
                .factionList(factionDtos)
                .categoryId(surveyEntity.getId())
                .categoryType(surveyEntity.getTitle())
                .build();
    }

    @Override
    public SurveyResultDto getOptionResult(UUID requestId) {
        List<SurveyItemOptionResultEntity> surveyResultEntities = surveyRepository.findSurveyOptionResult(requestId);
        SurveyEntity surveyEntity = surveyResultEntities.get(0)
                .getSurveyItemOptionEntity()
                .getSurveyItemEntity()
                .getSurveySubCategoryEntity()
                .getSurveyEntity();

        /*
         D_Stype 1 이 countif(ExamExe01>ExamExe02) 일때,
 D_Stype 2 이 countif(ExamExe01>ExamExe02)
 D_Stype 3 이 countif(ExamExe01>ExamExe02)
 D_Stype 4 이 countif(ExamExe01>ExamExe02) 이면 , 결과값 D_Ttype1, D_Stype1, D_Rtype1~4

         */

        Map<String, Map<String, Long>> cntBySubCategory = getCounts(surveyResultEntities, surveyEntity);

        List<FactionDto> factionDtos = getFactions(surveyEntity);

        return SurveyResultDto.builder()
                .factionList(factionDtos)
                .categoryId(surveyEntity.getId())
                .categoryType(surveyEntity.getTitle())
                .build();
    }

    private Map<String, Map<String, Long>> getCounts(List<SurveyItemOptionResultEntity> surveyResultEntities, SurveyEntity surveyEntity) {
        Map<String, Map<String, Long>> result = new HashMap<>();
        for(SurveySubCategoryEntity subCategory : surveyEntity.getSurveySubCategories()){
            result.put(subCategory.getCode(), getCountByMap(surveyResultEntities, subCategory));
        }

        return result;
    }

    private Map<String, Long> getCountByMap(List<SurveyItemOptionResultEntity> surveyResultEntities, SurveySubCategoryEntity subCategory) {
        Map<String, Long> result = new HashMap<>();
        result.put("total", (long) subCategory.getSurveyItemEntities().size());
        for(SurveyItemEntity surveyItem : subCategory.getSurveyItemEntities()){

            List<SurveyItemOptionEntity> surveyItemOptions = surveyItem.getSurveyItemOptionEntities();
            for(SurveyItemOptionEntity surveyItemOption : surveyItemOptions) {

                Long cnt = null == result.get(surveyItemOption.getOrderNum().toString()) ? 0L : result.get(surveyItemOption.getOrderNum().toString());
                cnt += surveyResultEntities.stream()
                        .filter(surveyResult -> surveyResult.getSurveyItemOptionEntity().equals(surveyItemOption)
                                && "Y".equals(surveyResult.getChecked()))
                        .count();
                result.put(surveyItemOption.getOrderNum().toString(), cnt);
            }
        }
        return result;
    }


    private List<FactionDto> getFactions(SurveyEntity surveyEntity) {
        List<FactionDto> result = new ArrayList<>();
        for(SurveySubCategoryEntity surveySubCategoryEntity: surveyEntity.getSurveySubCategories()){
            int totalScore = 0;
            for (SurveyItemEntity t : surveySubCategoryEntity.getSurveyItemEntities()) {
                for (SurveyResultEntity surveyResult : t.getSurveyResultEntities()) {
                    totalScore += surveyResult.getTotalScore();
                }
            }

            SurveySubResultEntity surveySubResultEntity =
                    surveySubResultRepository.get(surveySubCategoryEntity.getId(), totalScore);

            result.add(FactionDto.builder()
                            .faction(surveySubCategoryEntity.getTitle())
                            .score(totalScore)
                            .title(surveySubResultEntity.getResult())
                    .build());
        }

        return result;
    }

    private void assembleResultToSurveyEntity(List<SurveyResultEntity> surveyResultEntities, SurveyEntity surveyEntity) {
        for (SurveySubCategoryEntity subCategory : surveyEntity.getSurveySubCategories()) {
            for (SurveyItemEntity item : subCategory.getSurveyItemEntities()) {
                if (null == item.getSurveyResultEntities()) {
                    item.setSurveyResultEntities(new ArrayList<>());
                }
                List<SurveyResultEntity> results = item.getSurveyResultEntities();

                for(SurveyResultEntity surveyResultEntity: surveyResultEntities){
                    if(item.getId().equals(surveyResultEntity.getSurveyItemId())){
                        results.add(surveyResultEntity);
                        break;
                    }
                }
            }
        }
    }
}
