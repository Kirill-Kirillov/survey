package com.kirillov.survey_manager.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class QuestionDto {
    private Long id;
    private String text;
    private int order;
    @JsonIgnore
    private SurveyDto surveyDto;
}
