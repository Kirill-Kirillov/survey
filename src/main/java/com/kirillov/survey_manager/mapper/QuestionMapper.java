package com.kirillov.survey_manager.mapper;


import com.kirillov.survey_manager.entity.Question;
import com.kirillov.survey_manager.entity.QuestionDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface QuestionMapper {

    QuestionMapper QUESTION_MAPPER = Mappers.getMapper(QuestionMapper.class);

    Question toQuestion(QuestionDto questionDto);

    @InheritInverseConfiguration
    QuestionDto fromQuestion(Question question);
}
