package com.kirillov.survey_manager.controller;

import com.kirillov.survey_manager.entity.SurveyActive;
import com.kirillov.survey_manager.entity.SurveyDto;
import com.kirillov.survey_manager.service.SurveyRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("/survey-api")
public class SurveyCrudController {

    final SurveyRepositoryService surveyRepositoryService;

    @Autowired
    public SurveyCrudController(SurveyRepositoryService surveyRepositoryService) {
        this.surveyRepositoryService = surveyRepositoryService;
    }

    @PostMapping("/surveys")
    public ResponseEntity<SurveyDto> createSurvey(@Valid @RequestBody SurveyDto surveyDto){
    return surveyRepositoryService.createSurvey(surveyDto);
    }

    @PutMapping("/surveys/{id}")
    public ResponseEntity<SurveyDto> editSurvey(@PathVariable long id, @RequestBody SurveyDto surveyDto) {
        return surveyRepositoryService.changeSurvey(surveyDto, id);
    }

    @GetMapping("/surveys")
    public ResponseEntity<List<SurveyDto>> getSurveys(
            @RequestParam(required = false, name = "name") String name,
            @RequestParam(required = false,name = "date") Date date,
            @RequestParam(required = false,name = "active") SurveyActive active,
            @RequestParam(name = "sortBy") String sort){
        return surveyRepositoryService.getSurveys(name, date, active, sort);
    }

    @DeleteMapping("/surveys/{id}")
    public ResponseEntity<String> deleteSurvey(@PathVariable long id){
        return surveyRepositoryService.deleteSurvey(id);
    }



}
