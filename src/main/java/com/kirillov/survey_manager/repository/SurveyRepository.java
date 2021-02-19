package com.kirillov.survey_manager.repository;

import com.kirillov.survey_manager.entity.Survey;
import com.kirillov.survey_manager.entity.SurveyActive;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
    List<Survey> findAllByDayOfEndAfterOrderByDayOfEnd(Date date);
    List<Survey> findAllByDayOfEndAfterOrderByName(Date date);
    List<Survey> findAllByNameOrderByDayOfEnd(String name);
    List<Survey> findAllByNameOrderByName(String name);
    List<Survey> findAllBySurveyActiveOrderByName(SurveyActive surveyActive);
    List<Survey> findAllBySurveyActiveOrderByDayOfEnd(SurveyActive surveyActive);
    List<Survey> findAllByOrderByNameAsc();
    List<Survey> findAllByOrderByDayOfEndAsc();
}
