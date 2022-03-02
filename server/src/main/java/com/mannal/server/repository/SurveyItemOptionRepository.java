package com.mannal.server.repository;

import com.mannal.server.entity.survey.SurveyItemOptionEntity;

import java.util.UUID;

public interface SurveyItemOptionRepository {
    SurveyItemOptionEntity get(UUID id);
}