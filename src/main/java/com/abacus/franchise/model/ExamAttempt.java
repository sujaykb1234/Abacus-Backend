package com.abacus.franchise.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKeyColumn;

@Entity
public class ExamAttempt {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long attemptId;

	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;
	private String course_name;
	private String examTime;
	private String examName;
	private int imageQuePercentage;

	@ElementCollection
	@CollectionTable(name = "exam_attempt_questions", joinColumns = @JoinColumn(name = "attempt_id"))
	@Column(name = "question_id")
	private List<Long> questionIds; // Store IDs of questions

	@ElementCollection
	@CollectionTable(name = "exam_attempt_answers", joinColumns = @JoinColumn(name = "attempt_id"))
	@MapKeyColumn(name = "question_id")
	@Column(name = "answer")
	private Map<Long, String> answers = new HashMap<>(); // Store correct answers by question ID

	@ElementCollection
	@CollectionTable(name = "student_selected_answers", joinColumns = @JoinColumn(name = "attempt_id"))
	@MapKeyColumn(name = "question_id")
	@Column(name = "answer")
	private Map<Long, String> studentAnswers = new HashMap<>(); // Store student's selected answers

	private int question_count;
	
	private boolean status = true;

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getExamTime() {
		return examTime;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public void setExamTime(String examTime) {
		this.examTime = examTime;
	}

	public String getCourse_name() {
		return course_name;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	public int getQuestion_count() {
		return question_count;
	}

	public void setQuestion_count(int question_count) {
		this.question_count = question_count;
	}

	public Long getAttemptId() {
		return attemptId;
	}

	public void setAttemptId(Long attemptId) {
		this.attemptId = attemptId;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public List<Long> getQuestionIds() {
		return questionIds;
	}

	public void setQuestionIds(List<Long> questionIds) {
		this.questionIds = questionIds;
	}

	public Map<Long, String> getAnswers() {
		return answers;
	}

	public void setAnswers(Map<Long, String> answers) {
		this.answers = answers;
	}

	public Map<Long, String> getStudentAnswers() {
		return studentAnswers;
	}

	public void setStudentAnswers(Map<Long, String> studentAnswers) {
		this.studentAnswers = studentAnswers;
	}

	public int getImageQuePercentage() {
		return imageQuePercentage;
	}

	public void setImageQuePercentage(int imageQuePercentage) {
		this.imageQuePercentage = imageQuePercentage;
	}

	public ExamAttempt(Long attemptId, Course course, String course_name, String examTime, String examName,
			int imageQuePercentage, List<Long> questionIds, Map<Long, String> answers, Map<Long, String> studentAnswers,
			int question_count) {
		super();
		this.attemptId = attemptId;
		this.course = course;
		this.course_name = course_name;
		this.examTime = examTime;
		this.examName = examName;
		this.imageQuePercentage = imageQuePercentage;
		this.questionIds = questionIds;
		this.answers = answers;
		this.studentAnswers = studentAnswers;
		this.question_count = question_count;
	}

	public ExamAttempt() {
		super();
	}

}
