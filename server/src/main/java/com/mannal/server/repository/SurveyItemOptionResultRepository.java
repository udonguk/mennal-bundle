package com.mannal.server.repository;

import com.mannal.server.dto.StatisticDto;
import com.mannal.server.entity.survey.SurveyItemOptionResultEntity;

import java.util.List;

public interface SurveyItemOptionResultRepository {
    List<SurveyItemOptionResultEntity> get(String categoryCode);
}
