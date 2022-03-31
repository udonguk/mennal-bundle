package com.mannal.server.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemOptionSummary {
    private String optionType;
    private String title;
    private long score;
    private long sum;
}
