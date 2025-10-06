package com.abacus.franchise.dto;

import java.util.List;

public class ExamDTO {

	private Long id;
	private String courseName;
	private String examTime;
	private String examName;
	private Long course_id;
	private String course_name;
	private List<QuestionsDTO> questions;
	private int question_count;
	private int imageQuePercentage;

	

	public int getQuestion_count() {
		return question_count;
	}

	public void setQuestion_count(int question_count) {
		this.question_count = question_count;
	}

	public int getImageQuePercentage() {
		return imageQuePercentage;
	}

	public void setImageQuePercentage(int imageQuePercentage) {
		this.imageQuePercentage = imageQuePercentage;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getExamTime() {
		return examTime;
	}

	public void setExamTime(String examTime) {
		this.examTime = examTime;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public Long getCourse_id() {
		return course_id;
	}

	public void setCourse_id(Long course_id) {
		this.course_id = course_id;
	}

	public String getCourse_name() {
		return course_name;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	public List<QuestionsDTO> getQuestions() {
		return questions;
	}

	public void setQuestions(List<QuestionsDTO> questions) {
		this.questions = questions;
	}

}
