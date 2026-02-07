package com.abacus.franchise.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Questions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long que_id;
    private String que;
    private String correct_answer;
    private Boolean isImageQuestion;
    private String question_name;
    private String question_link;
    private String question_type; // TEXT /IMAGE / NUMERIC
    @Column(columnDefinition = "TEXT")
    private String options;
    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonIgnore
    private Course course;

    private boolean status = true;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Questions(Long que_id, String que, String correct_answer, Boolean isImageQuestion, String question_name,
                     String question_link, String question_type, String options, Course course) {
        super();
        this.que_id = que_id;
        this.que = que;
        this.correct_answer = correct_answer;
        this.isImageQuestion = isImageQuestion;
        this.question_name = question_name;
        this.question_link = question_link;
        this.question_type = question_type;
        this.options = options;
        this.course = course;
    }

    public Long getQue_id() {
        return que_id;
    }

    public void setQue_id(Long que_id) {
        this.que_id = que_id;
    }

    public String getQue() {
        return que;
    }

    public Boolean getIsImageQuestion() {
        return isImageQuestion;
    }

    public void setIsImageQuestion(Boolean isImageQuestion) {
        this.isImageQuestion = isImageQuestion;
    }

    public String getQuestion_name() {
        return question_name;
    }

    public void setQuestion_name(String question_name) {
        this.question_name = question_name;
    }

    public String getQuestion_link() {
        return question_link;
    }

    public void setQuestion_link(String question_link) {
        this.question_link = question_link;
    }

    public String getQuestion_type() {
        return question_type;
    }

    public void setQuestion_type(String question_type) {
        this.question_type = question_type;
    }

    public void setQue(String que) {
        this.que = que;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Questions() {
        super();
        // TODO Auto-generated constructor stub
    }

}
