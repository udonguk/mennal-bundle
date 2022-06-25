package com.mannal.server.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StatisticDto {
    String optionType;
    Long total;
    Integer checked;
    Integer unChecked;

//    @QueryProjection
//    public StatisticDto(String optionType, Integer total, Integer checked, Integer unChecked) {
//        this.optionType = optionType;
//        this.total = total;
//        this.checked = checked;
//        this.unChecked = unChecked;
//    }
}
