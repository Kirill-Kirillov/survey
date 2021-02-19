package com.kirillov.survey_manager.service;

import com.kirillov.survey_manager.entity.QuestionDto;
import org.springframework.http.ResponseEntity;

public interface QuestionRepositoryService {
    ResponseEntity<QuestionDto> addQuestion(QuestionDto questionDto, long id);
    ResponseEntity<QuestionDto> editQuestion(QuestionDto questionDto, long id);
    ResponseEntity<String> deleteQuestion(long id);
}
