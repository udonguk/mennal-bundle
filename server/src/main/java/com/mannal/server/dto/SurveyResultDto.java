package com.mannal.server.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class SurveyResultDto {
    UUID categoryId;
    String categoryType;
    List<FactionDto> factionList;
}
