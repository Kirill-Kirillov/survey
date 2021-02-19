package com.kirillov.survey_manager.service;

import com.kirillov.survey_manager.entity.Question;
import com.kirillov.survey_manager.entity.QuestionDto;
import com.kirillov.survey_manager.entity.Survey;
import com.kirillov.survey_manager.mapper.QuestionMapper;
import com.kirillov.survey_manager.repository.QuestionRepository;
import com.kirillov.survey_manager.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class QuestionRepositoryServiceImpl implements QuestionRepositoryService{

    final QuestionRepository questionRepository;
    final SurveyRepository surveyRepository;

    @Autowired
    public QuestionRepositoryServiceImpl(QuestionRepository questionRepository, SurveyRepository surveyRepository) {
        this.questionRepository = questionRepository;
        this.surveyRepository = surveyRepository;
    }

    @Override
    public ResponseEntity<QuestionDto> addQuestion(QuestionDto questionDto, long id) {
        Optional<Survey> optionalSurvey = surveyRepository.findById(id);
        if(optionalSurvey.isEmpty()) {
            throw new EntityNotFoundException("Опрос с id "+id+" отсутствует");
        }
        Survey survey = optionalSurvey.get();
        Question question = QuestionMapper.QUESTION_MAPPER.toQuestion(questionDto);
        question.setSurvey(survey);
        question = questionRepository.save(question);
        questionDto = QuestionMapper.QUESTION_MAPPER.fromQuestion(question);
        return new ResponseEntity<>(questionDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<QuestionDto> editQuestion(QuestionDto questionDto, long id) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);
        if(optionalQuestion.isEmpty()) {
            throw new EntityNotFoundException("Вопрос с id "+id+" отсутствует");
        }
        Question questionFromRequest = QuestionMapper.QUESTION_MAPPER.toQuestion(questionDto);
        Question questionFromDb = optionalQuestion.get();
        if (questionFromRequest.getOrder()!=0) {
            questionFromDb.setOrder(questionFromRequest.getOrder());
        }
        if (questionFromRequest.getText()!=null) {
            questionFromDb.setText(questionFromRequest.getText());
        }
        questionFromDb = questionRepository.save(questionFromDb);
        questionDto = QuestionMapper.QUESTION_MAPPER.fromQuestion(questionFromDb);
        return new ResponseEntity<>(questionDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteQuestion(long id) {
        Question question = questionRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Опрос с id " + id + " не найден!"));
        questionRepository.delete(question);
        return new ResponseEntity<>("Успешное удаление вопроса с id "+ id, HttpStatus.OK);
    }
}
