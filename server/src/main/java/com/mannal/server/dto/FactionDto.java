package com.mannal.server.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FactionDto {
    String faction;
    String resultType;

    String title;
    Integer score;
}
