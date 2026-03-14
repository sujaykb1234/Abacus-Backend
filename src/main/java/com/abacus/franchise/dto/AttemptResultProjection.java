package com.abacus.franchise.dto;

public interface AttemptResultProjection {

    Integer getAttemptNo();
    Long getTotalQuestions();
    Long getCorrectQuestions();
    Long getMarks();
}