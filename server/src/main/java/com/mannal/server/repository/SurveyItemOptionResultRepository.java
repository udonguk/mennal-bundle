package com.mannal.server.repository;

import com.mannal.server.dto.StatisticDto;

import java.util.List;

public interface SurveyItemOptionResultRepository {
    List<StatisticDto> getBarStatistic(String categoryCode);

    List<StatisticDto> getRangeStatistic(String categoryCode);
}
