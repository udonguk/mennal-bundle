package com.mannal.server.service.survey.impl;

import com.mannal.server.dto.FactionDto;
import com.mannal.server.dto.StatisticDto;
import com.mannal.server.dto.SurveyResultDto;
import com.mannal.server.entity.survey.*;
import com.mannal.server.repository.*;
import com.mannal.server.service.survey.SurveyService;
import com.mannal.server.vo.ItemOptionSummary;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("surveyService")
public class SurveyServiceImpl implements SurveyService {

    private final SurveyCategoryRepository surveyCategoryRepository;

    private final SurveyRepository surveyRepository;

    private final SurveyItemOptionRepository surveyItemOptionRepository;

    private final SurveyResultRepository surveyResultRepository;

    private final SurveyItemOptionResultRepository surveyItemOptionResultRepository;

    public SurveyServiceImpl(SurveyCategoryRepository surveyCategoryRepository,
                             SurveyRepository surveyRepository,
                             SurveyItemOptionRepository surveyItemOptionRepository,
                             SurveyResultRepository surveyResultRepository,
                             SurveyItemOptionResultRepository surveyItemOptionResultRepository) {
        this.surveyCategoryRepository = surveyCategoryRepository;
        this.surveyRepository = surveyRepository;
        this.surveyItemOptionRepository = surveyItemOptionRepository;
        this.surveyResultRepository = surveyResultRepository;
        this.surveyItemOptionResultRepository = surveyItemOptionResultRepository;
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
    public void saveOptionResult(List<SurveyItemOptionResultEntity> surveyItemOptionResultEntityList) {
        for (SurveyItemOptionResultEntity surveyItemOptionResult : surveyItemOptionResultEntityList) {
            surveyItemOptionResult.setSurveyItemOptionEntity(
                    surveyItemOptionRepository.get(surveyItemOptionResult.getSurveyItemOptionEntity().getId())
            );
        }
        surveyRepository.savesOptionResult(surveyItemOptionResultEntityList);
    }

    @Override
    public SurveyResultDto getOptionResult(UUID requestId) {
        List<SurveyItemOptionResultEntity> surveyResultEntities =
                surveyRepository.findSurveyOptionResult(requestId);
        SurveyEntity surveyEntity = surveyResultEntities.get(0)
                .getSurveyItemOptionEntity()
                .getSurveyItemEntity()
                .getSurveySubCategoryEntity()
                .getSurveyEntity();

        Map<String, Map<String, ItemOptionSummary>> cntBySubCategory =
                getCounts(surveyResultEntities, surveyEntity);
        List<SurveyResultEntity> surveyResultEntityList =
                surveyResultRepository.findBySurveyCategoryId(surveyEntity.getId());
        List<SurveyResultEntity> resultList = calculateSurveyResult(cntBySubCategory, surveyResultEntityList);

        List<List<FactionDto>> factionDtoList = new ArrayList<>();
        cntBySubCategory.forEach((key, value) -> {
            List<FactionDto> dtoList = new ArrayList<>();
            value.forEach((subCategoryKey, subCategoryValue) -> {
                if(!"total".equals(subCategoryKey)){
                    dtoList.add(FactionDto.builder()
                            .title(subCategoryValue.getTitle())
                            .score((int) subCategoryValue.getSum())
                            .resultType(subCategoryValue.getOptionType())
                            .faction(subCategoryValue.getOptionType())
                            .build());
                }
            });
            factionDtoList.add(dtoList);
        });

        List<StatisticDto> statisticList = null;
        switch (surveyEntity.getGraphType()) {
            case "range":
                statisticList = surveyItemOptionResultRepository.getRangeStatistic(surveyEntity.getCode());
                break;
            case "bar":
                statisticList = surveyItemOptionResultRepository.getBarStatistic(surveyEntity.getCode());
                break;
            default:
                statisticList = null;
        }

        return SurveyResultDto.builder()
                .graphType(surveyEntity.getGraphType())
                .factionList(factionDtoList)
                .resultList(resultList)
                .categoryId(surveyEntity.getId())
                .categoryType(surveyEntity.getTitle())
                .statisticList(statisticList)
                .build();
    }

    private List<SurveyResultEntity> calculateSurveyResult(
            Map<String, Map<String, ItemOptionSummary>> cntBySubCategory
            , List<SurveyResultEntity> surveyResultEntityList
    ) {
        List<SurveyResultEntity> results = new ArrayList<>();

        for(SurveyResultEntity surveyResultEntity: surveyResultEntityList){
            boolean isAllPass = true;

            for(Role role : surveyResultEntity.getRole()) {
                // 룰의 코드를 읽어서 해당 룰이 어떤 서브타입에서 조회되어야 하는지 조회
                String code = role.getCode();
                // 서브 타입에 따른 examExe 정보를 읽음
                Map<String, ItemOptionSummary> subCategoryCnt = cntBySubCategory.get(code);
                long total = subCategoryCnt.get("total").getSum();
                long examExe01 = null == subCategoryCnt.get("1") ? 0L : subCategoryCnt.get("1").getSum();
                long examExe02 = null == subCategoryCnt.get("2") ? 0L : subCategoryCnt.get("2").getSum();
                long examExe03 = null == subCategoryCnt.get("3") ? 0L : subCategoryCnt.get("3").getSum();
                long examExe04 = null == subCategoryCnt.get("4") ? 0L : subCategoryCnt.get("4").getSum();
                long examExe05 = null == subCategoryCnt.get("5") ? 0L : subCategoryCnt.get("5").getSum();
                long examExe06 = null == subCategoryCnt.get("6") ? 0L : subCategoryCnt.get("6").getSum();
                // 조회된 examExe 에 따라 결과 출력
                Expression exp = new ExpressionBuilder(role.getCondition())
                        .variables("TOTAL", "V01", "V02", "V03", "V04", "V05", "V06")
                        .build()
                        .setVariable("TOTAL", total)
                        .setVariable("V01", examExe01)
                        .setVariable("V02", examExe02)
                        .setVariable("V03", examExe03)
                        .setVariable("V04", examExe04)
                        .setVariable("V05", examExe05)
                        .setVariable("V06", examExe06);

                if(0 > exp.evaluate()){
                    isAllPass = false;
                    break;
                }
            }

            if(isAllPass)
                results.add(surveyResultEntity);
        }

        return results;
    }

    private Map<String, Map<String, ItemOptionSummary>> getCounts(
            List<SurveyItemOptionResultEntity> surveyResultEntities,
            SurveyEntity surveyEntity
    ) {
        Map<String, Map<String, ItemOptionSummary>> result = new HashMap<>();
        for(SurveySubCategoryEntity subCategory : surveyEntity.getSurveySubCategories()){
            result.put(subCategory.getCode(), getCountByMap(surveyResultEntities, subCategory));
        }

        return result;
    }

    private Map<String, ItemOptionSummary> getCountByMap(
            List<SurveyItemOptionResultEntity> surveyResultEntities,
            SurveySubCategoryEntity subCategory
    ) {
        Map<String, ItemOptionSummary> result = new HashMap<>();
        result.put("total", ItemOptionSummary.builder()
                .title("total")
                .sum(subCategory.getSurveyItemEntities().size())
                .build());

        for(SurveyItemEntity surveyItem : subCategory.getSurveyItemEntities()){

            List<SurveyItemOptionEntity> surveyItemOptions = surveyItem.getSurveyItemOptionEntities();

            for(SurveyItemOptionEntity surveyItemOption : surveyItemOptions) {
                long cnt = null == result.get(surveyItemOption.getOrderNum().toString()) ?
                        0L : result.get(surveyItemOption.getOrderNum().toString()).getSum();
                cnt += surveyResultEntities.stream()
                        .filter(surveyResult -> surveyResult.getSurveyItemOptionEntity().equals(surveyItemOption)
                                && "Y".equals(surveyResult.getChecked()))
                        .count();
                result.put(
                        surveyItemOption.getOrderNum().toString(),
                        ItemOptionSummary.builder()
                                .title(surveyItemOption.getTitle())
                                .optionType(surveyItemOption.getOptionType())
                                .score(surveyItemOption.getScore())
                                .sum(cnt)
                                .build());
            }
        }
        return result;
    }
}
