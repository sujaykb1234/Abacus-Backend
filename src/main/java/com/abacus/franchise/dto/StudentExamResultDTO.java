package com.abacus.franchise.dto;

import java.util.List;

import com.abacus.franchise.utility.ExamStatus;

public class StudentExamResultDTO {

	private Long id;
	private String studentName;
	private String examName;
	private Long examId;
	private Integer totalQuestions;
	private Integer attemptedQuestion;
	private Integer totalCorrectAnswers;
	private List<QuestionsDTO> questions;
	private Long courseId;
	private String courseName;
	private String testTime;
	private Long StudentId;
	private String examStartTime;
	private String examSubmissionTime;
	private Integer marks;
	private ExamStatus examStatus = ExamStatus.COMPLETED;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public Long getExamId() {
		return examId;
	}

	public void setExamId(Long examId) {
		this.examId = examId;
	}

	public Integer getTotalQuestions() {
		return totalQuestions;
	}

	public void setTotalQuestions(Integer totalQuestions) {
		this.totalQuestions = totalQuestions;
	}

	public Integer getAttemptedQuestion() {
		return attemptedQuestion;
	}

	public void setAttemptedQuestion(Integer attemptedQuestion) {
		this.attemptedQuestion = attemptedQuestion;
	}

	public Integer getTotalCorrectAnswers() {
		return totalCorrectAnswers;
	}

	public void setTotalCorrectAnswers(Integer totalCorrectAnswers) {
		this.totalCorrectAnswers = totalCorrectAnswers;
	}

	public List<QuestionsDTO> getQuestions() {
		return questions;
	}

	public void setQuestions(List<QuestionsDTO> questions) {
		this.questions = questions;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Long getStudentId() {
		return StudentId;
	}

	public void setStudentId(Long studentId) {
		StudentId = studentId;
	}

	public double getMarks() {
		return marks;
	}

	public ExamStatus getExamStatus() {
		return examStatus;
	}

	public void setExamStatus(ExamStatus examStatus) {
		this.examStatus = examStatus;
	}

	public String getExamStartTime() {
		return examStartTime;
	}

	public void setExamStartTime(String examStartTime) {
		this.examStartTime = examStartTime;
	}

	public String getExamSubmissionTime() {
		return examSubmissionTime;
	}

	public void setExamSubmissionTime(String examSubmissionTime) {
		this.examSubmissionTime = examSubmissionTime;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getTestTime() {
		return testTime;
	}

	public void setTestTime(String testTime) {
		this.testTime = testTime;
	}

	public StudentExamResultDTO(Long id, String studentName, String examName, Long examId, Integer totalQuestions,
			Integer attemptedQuestion, Integer totalCorrectAnswers, List<QuestionsDTO> questions, Long courseId,
			String courseName, String testTime, Long studentId, String examStartTime, String examSubmissionTime,
			Integer marks, ExamStatus examStatus) {
		super();
		this.id = id;
		this.studentName = studentName;
		this.examName = examName;
		this.examId = examId;
		this.totalQuestions = totalQuestions;
		this.attemptedQuestion = attemptedQuestion;
		this.totalCorrectAnswers = totalCorrectAnswers;
		this.questions = questions;
		this.courseId = courseId;
		this.courseName = courseName;
		this.testTime = testTime;
		StudentId = studentId;
		this.examStartTime = examStartTime;
		this.examSubmissionTime = examSubmissionTime;
		this.marks = marks;
		this.examStatus = examStatus;
	}

	public void setMarks(Integer marks) {
		this.marks = marks;
	}

	public StudentExamResultDTO() {
		super();
	}

}
