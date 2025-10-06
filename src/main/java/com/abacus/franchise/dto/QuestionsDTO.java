package com.abacus.franchise.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class QuestionsDTO {

	private Long que_id;

	@NotBlank(message = "Questions must not be null")
	private String que;

	private String correct_answer;

	@NotBlank(message = "Options must not be null")
	private String options;

	private String chosenOption;

	private Boolean isImageQuestion;
	private String question_name;
	private String question_link;
	private String question_type; // TEXT /IMAGE /NUMBER

	private CourseResponseDTO course;

	public QuestionsDTO(Long que_id, @NotBlank(message = "Questions must not be null") String que,
			String correct_answer, @NotBlank(message = "Options must not be null") String options, String chosenOption,
			Boolean isImageQuestion, String question_name, String question_link, String question_type,
			@NotNull(message = "Course must not be null") CourseResponseDTO course) {
		super();
		this.que_id = que_id;
		this.que = que;
		this.correct_answer = correct_answer;
		this.options = options;
		this.chosenOption = chosenOption;
		this.isImageQuestion = isImageQuestion;
		this.question_name = question_name;
		this.question_link = question_link;
		this.question_type = question_type;
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

	public CourseResponseDTO getCourse() {
		return course;
	}

	public void setCourse(CourseResponseDTO course) {
		this.course = course;
	}

	public QuestionsDTO() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getChosenOption() {
		return chosenOption;
	}

	public void setChosenOption(String chosenOption) {
		this.chosenOption = chosenOption;
	}

}
