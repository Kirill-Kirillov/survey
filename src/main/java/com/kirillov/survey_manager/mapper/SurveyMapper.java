package com.kirillov.survey_manager.mapper;

import com.kirillov.survey_manager.entity.Survey;
import com.kirillov.survey_manager.entity.SurveyDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = { QuestionMapper.class })
public interface SurveyMapper {

    SurveyMapper SURVEY_MAPPER = Mappers.getMapper(SurveyMapper.class);

    Survey toSurvey(SurveyDto surveyDto);

    @InheritInverseConfiguration
    SurveyDto fromSurvey(Survey survey);
}
