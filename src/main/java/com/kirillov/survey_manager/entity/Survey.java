package com.kirillov.survey_manager.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Table(name = "surveys")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Survey {

    @Id
    @Column(name = "surveys_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "surveys_name")
    private String name;

    @Column(name = "surveys_day_start")
    private Date dayOfStart;

    @Column(name = "surveys_day_end")
    private Date dayOfEnd;

    @Column(name = "surveys_is_active")
    @Enumerated(EnumType.STRING)
    private SurveyActive surveyActive;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "survey")
    private List<Question> questions;

}
