package com.abacus.franchise.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class StudentPracticeTests {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String examName;
	private String startTime;
	private Long courseId;
	private int totalQuestion;
	private String submissionTime;
	private Double score;
	private Long studentId;

	public Long getId() {
		return id;
	}

	public int getTotalQuestion() {
		return totalQuestion;
	}

	public void setTotalQuestion(int totalQuestion) {
		this.totalQuestion = totalQuestion;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getSubmissionTime() {
		return submissionTime;
	}

	public void setSubmissionTime(String submissionTime) {
		this.submissionTime = submissionTime;
	}

	public StudentPracticeTests(Long id, String examName, String startTime, Long courseId, int totalQuestion,
			String submissionTime, Double score, Long studentId) {
		super();
		this.id = id;
		this.examName = examName;
		this.startTime = startTime;
		this.courseId = courseId;
		this.totalQuestion = totalQuestion;
		this.submissionTime = submissionTime;
		this.score = score;
		this.studentId = studentId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public StudentPracticeTests() {
		super();
		// TODO Auto-generated constructor stub
	}

}
