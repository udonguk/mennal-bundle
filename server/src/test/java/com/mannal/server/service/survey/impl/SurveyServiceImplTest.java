package com.mannal.server.service.survey.impl;

import com.mannal.server.entity.survey.SurveyCategoryEntity;
import com.mannal.server.repository.SurveyCategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

//@SpringBootTest
@ExtendWith(MockitoExtension.class) // Mockito의 Mock 객체를 사용하기 위한 annotation
class SurveyServiceImplTest {

    @InjectMocks
    private SurveyServiceImpl surveyService;

    @Mock
    private SurveyCategoryRepository surveyCategoryRepository;

    @Test
    public void getAllSurveys() {
        // given
        SurveyCategoryEntity surveyCategoryEntity1 = SurveyCategoryEntity.builder()
                .id(UUID.randomUUID())
                .title("TEST_surveyCategory1")
                .regDt(Date.valueOf(LocalDate.now()))
                .editDt(Date.valueOf(LocalDate.now()))
                .build();

        SurveyCategoryEntity surveyCategoryEntity2 = SurveyCategoryEntity.builder()
                .id(UUID.randomUUID())
                .title("TEST_surveyCategory2")
                .regDt(Date.valueOf(LocalDate.now()))
                .editDt(Date.valueOf(LocalDate.now()))
                .build();

        List<SurveyCategoryEntity> surveyCategoryEntityList = new ArrayList<>();
        surveyCategoryEntityList.add(surveyCategoryEntity1);
        surveyCategoryEntityList.add(surveyCategoryEntity2);

        // mocking
        given(surveyCategoryRepository.findAllSurveyCategory())
                .willReturn(surveyCategoryEntityList);

        // when
        List<SurveyCategoryEntity> surveyCategoriesResult = surveyService.getAllSurveys();

        // then
        assertThat(surveyCategoriesResult.size()).isEqualTo(2);


    }
}