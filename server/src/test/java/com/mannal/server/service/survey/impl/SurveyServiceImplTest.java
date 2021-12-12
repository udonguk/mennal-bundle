package com.mannal.server.service.survey.impl;

import com.mannal.server.entity.survey.SurveyCategory;
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
    public void getAllSurveys() throws Exception {
        // given
        SurveyCategory surveyCategory1 = SurveyCategory.builder()
                .id(UUID.randomUUID())
                .title("TEST_surveyCategory1")
                .reg_dt(Date.valueOf(LocalDate.now()))
                .edit_dt(Date.valueOf(LocalDate.now()))
                .build();

        SurveyCategory surveyCategory2 = SurveyCategory.builder()
                .id(UUID.randomUUID())
                .title("TEST_surveyCategory2")
                .reg_dt(Date.valueOf(LocalDate.now()))
                .edit_dt(Date.valueOf(LocalDate.now()))
                .build();

        List<SurveyCategory> surveyCategoryList = new ArrayList<>();
        surveyCategoryList.add(surveyCategory1);
        surveyCategoryList.add(surveyCategory2);

        // mocking
        given(surveyCategoryRepository.findAllSurveyCategory())
                .willReturn(surveyCategoryList);

        // when
        List<SurveyCategory> surveyCategoriesResult = surveyService.getAllSurveys();

        // then
        assertThat(surveyCategoriesResult.size()).isEqualTo(2);


    }
}