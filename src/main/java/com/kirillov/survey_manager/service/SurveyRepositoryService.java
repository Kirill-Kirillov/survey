package com.kirillov.survey_manager.service;

import com.kirillov.survey_manager.entity.Survey;
import com.kirillov.survey_manager.entity.SurveyActive;
import com.kirillov.survey_manager.entity.SurveyDto;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.sql.Date;
import java.util.List;

public interface SurveyRepositoryService {
    ResponseEntity<SurveyDto> createSurvey(SurveyDto survey);

    ResponseEntity<SurveyDto> changeSurvey(SurveyDto surveyDto, long id) throws EntityNotFoundException;

    ResponseEntity<List<SurveyDto>> getSurveys(String name, Date date, SurveyActive active, String sort);

    ResponseEntity<String> deleteSurvey(long id);
}
