package com.kirillov.survey_manager.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class SurveyDto {
    private Long id;
    private String name;
    private Date dayOfStart;
    private Date dayOfEnd;
    private SurveyActive surveyActive;
    private List<QuestionDto> questions = new ArrayList<>();
}
