package com.kirillov.survey_manager.service;

import com.kirillov.survey_manager.entity.*;
import com.kirillov.survey_manager.mapper.SurveyMapper;
import com.kirillov.survey_manager.repository.QuestionRepository;
import com.kirillov.survey_manager.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SurveyRepositoryServiceImpl implements SurveyRepositoryService {

    final SurveyRepository surveyRepository;
    final QuestionRepository questionRepository;

    @Autowired
    public SurveyRepositoryServiceImpl(SurveyRepository surveyRepository, QuestionRepository questionRepository) {
        this.surveyRepository = surveyRepository;
        this.questionRepository = questionRepository;
    }


    @Override
    public ResponseEntity<SurveyDto> createSurvey(SurveyDto surveyDto) {
        Survey survey = SurveyMapper.SURVEY_MAPPER.toSurvey(surveyDto);
        if(!survey.getQuestions().isEmpty()) {
            for(Question question : survey.getQuestions()){
                question.setSurvey(survey);
            }
        }
        survey = surveyRepository.save(survey);
        surveyDto = SurveyMapper.SURVEY_MAPPER.fromSurvey(survey);
        return new ResponseEntity<>(surveyDto, HttpStatus.CREATED);
    }


    @Override
    public ResponseEntity<SurveyDto> changeSurvey(SurveyDto surveyDto, long id) throws EntityNotFoundException{
        Survey surveyFromRequest = SurveyMapper.SURVEY_MAPPER.toSurvey(surveyDto);
        Optional<Survey> optionalSurvey = surveyRepository.findById(id);
        if(optionalSurvey.isEmpty()) {
            throw new EntityNotFoundException("Опрос с id "+id+" отсутствует");
        }
        Survey surveyFromDb = optionalSurvey.get();
        if (surveyFromRequest.getName()!=null) {
            surveyFromDb.setName(surveyFromRequest.getName());
        }
        if (surveyFromRequest.getSurveyActive()!=null) {
            surveyFromDb.setSurveyActive(surveyFromRequest.getSurveyActive());
        }
        if  (surveyFromRequest.getDayOfEnd()!=null) {
            surveyFromDb.setDayOfEnd(surveyFromRequest.getDayOfEnd());
        }
        if (surveyFromRequest.getDayOfStart()!=null) {
            surveyFromDb.setDayOfStart(surveyFromRequest.getDayOfStart());
        }
        surveyFromDb = surveyRepository.save(surveyFromDb);
        surveyDto = SurveyMapper.SURVEY_MAPPER.fromSurvey(surveyFromDb);
            return new ResponseEntity<>(surveyDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<SurveyDto>> getSurveys(String name, Date date, SurveyActive active, String sort) {
        List<Survey> surveys = new ArrayList<>();
        List<SurveyDto> surveyDtos = new ArrayList<>();
        if (name!=null) {
            if (sort.equals("name")) {
                surveys = surveyRepository.findAllByNameOrderByName(name);
            }
            else if (sort.equals("date")) {
                surveys = surveyRepository.findAllByNameOrderByDayOfEnd(name);
            }
        }

        else if (date!=null) {
            if (sort.equals("name")) {
                surveys = surveyRepository.findAllByDayOfEndAfterOrderByName(date);
            }
            else if (sort.equals("date")) {
                surveys = surveyRepository.findAllByDayOfEndAfterOrderByDayOfEnd(date);
            }
        }

        else if (active!=null) {
            if (sort.equals("name")) {
                surveys = surveyRepository.findAllBySurveyActiveOrderByName(active);
            }
            else if (sort.equals("date")) {
                surveys = surveyRepository.findAllBySurveyActiveOrderByDayOfEnd(active);
            }
        }

        else {
            if (sort.equals("name")) {
                surveys = surveyRepository.findAllByOrderByNameAsc();
            }
            else if (sort.equals("date")) {
                surveys = surveyRepository.findAllByOrderByDayOfEndAsc();
            }
        }

        if (!surveys.isEmpty()) {
            for (Survey survey : surveys) {
                surveyDtos.add(SurveyMapper.SURVEY_MAPPER.fromSurvey(survey));
            }
        }
        else {
            throw new EntityNotFoundException("Не удалось найти опросы, соответсующие запросу");
        }

        return new ResponseEntity<>(surveyDtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteSurvey(long id) {
        Survey survey = surveyRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Опрос с id " + id + " не найден!"));
        surveyRepository.delete(survey);
        return new ResponseEntity<>("Успешное удаление опроса с id "+ id, HttpStatus.OK);
    }
}
