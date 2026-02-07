package com.abacus.franchise.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class Answers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student student;

	@ManyToOne
	@JoinColumn(name = "question_id")
	@JsonIgnore
	private Questions question;

	private String chosenOption;

	private String correctOption;

	private String questionType;
//
	@ManyToOne
	private Course course;

	@ManyToOne
	@JoinColumn(name = "attemptId")
	private ExamAttempt attempt;

	public Long getId() {
		return id;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public String getCorrectOption() {
		return correctOption;
	}

	public void setCorrectOption(String correctOption) {
		this.correctOption = correctOption;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Questions getQuestion() {
		return question;
	}

	public void setQuestion(Questions question) {
		this.question = question;
	}

	public String getChosenOption() {
		return chosenOption;
	}

	public void setChosenOption(String chosenOption) {
		this.chosenOption = chosenOption;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public ExamAttempt getAttempt() {
		return attempt;
	}

	public void setAttempt(ExamAttempt attempt) {
		this.attempt = attempt;
	}

	public Answers(Long id, Student student, Questions question, String chosenOption, String correctOption,
			String questionType, Course course, ExamAttempt attempt) {
		super();
		this.id = id;
		this.student = student;
		this.question = question;
		this.chosenOption = chosenOption;
		this.correctOption = correctOption;
		this.questionType = questionType;
		this.course = course;
		this.attempt = attempt;
	}

	public Answers() {
		super();
		// TODO Auto-generated constructor stub
	}

}
