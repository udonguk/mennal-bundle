package com.mannal.server.dto;

import com.mannal.server.entity.survey.SurveyResultEntity;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class SurveyResultDto {
    UUID categoryId;
    String categoryType;
    String graphType;
    List<List<FactionDto>> factionList;
    List<SurveyResultEntity> resultList;
    List<StatisticDto> statisticList;
}
