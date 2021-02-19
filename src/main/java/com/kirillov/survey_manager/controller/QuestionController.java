package com.kirillov.survey_manager.controller;

import com.kirillov.survey_manager.entity.QuestionDto;
import com.kirillov.survey_manager.service.QuestionRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/survey-api")
public class QuestionController {

    final QuestionRepositoryService questionRepositoryService;

    @Autowired
    public QuestionController(QuestionRepositoryService questionRepositoryService) {
        this.questionRepositoryService = questionRepositoryService;
    }

    @PostMapping("surveys/questions/{id}")
    public ResponseEntity<QuestionDto> addQuestion(@PathVariable long id, @RequestBody QuestionDto questionDto){
        return questionRepositoryService.addQuestion(questionDto, id);
    }

    @PutMapping("/questions/{id}")
    public ResponseEntity<QuestionDto> editQuestion(@PathVariable long id, @RequestBody QuestionDto questionDto) {
        return questionRepositoryService.editQuestion(questionDto, id);
    }

    @DeleteMapping("/questions/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable long id){
        return questionRepositoryService.deleteQuestion(id);
    }
}
