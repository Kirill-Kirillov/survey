package com.kirillov.survey_manager.repository;

import com.kirillov.survey_manager.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
