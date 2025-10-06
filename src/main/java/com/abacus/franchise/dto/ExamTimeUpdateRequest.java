package com.abacus.franchise.dto;

import java.util.List;

public class ExamTimeUpdateRequest {

	private List<Long> studentIds;
	private Long examId;
	private String newTimeInMinutes;

	public List<Long> getStudentIds() {
		return studentIds;
	}

	public void setStudentIds(List<Long> studentIds) {
		this.studentIds = studentIds;
	}

	public Long getExamId() {
		return examId;
	}

	public void setExamId(Long examId) {
		this.examId = examId;
	}

	public String getNewTimeInMinutes() {
		return newTimeInMinutes;
	}

	public void setNewTimeInMinutes(String newTimeInMinutes) {
		this.newTimeInMinutes = newTimeInMinutes;
	}

	public ExamTimeUpdateRequest(List<Long> studentIds, Long examId, String newTimeInMinutes) {
		super();
		this.studentIds = studentIds;
		this.examId = examId;
		this.newTimeInMinutes = newTimeInMinutes;
	}

	public ExamTimeUpdateRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Getters and Setters
}
