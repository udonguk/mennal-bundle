package com.mannal.server.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SurveyResultDto {
    String categoryType;
    List<FactionDto> factionList;
}
