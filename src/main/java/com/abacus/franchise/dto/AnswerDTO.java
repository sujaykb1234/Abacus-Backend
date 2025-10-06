package com.abacus.franchise.dto;

public class AnswerDTO {
	private Long id;
	private Long questionId;
	private String chosenOption;
	private String correctOption;

	public String getCorrectOption() {
		return correctOption;
	}

	public void setCorrectOption(String correctOption) {
		this.correctOption = correctOption;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public String getChosenOption() {
		return chosenOption;
	}

	public void setChosenOption(String chosenOption) {
		this.chosenOption = chosenOption;
	}

	public AnswerDTO(Long id, Long questionId, String chosenOption, String correctOption) {
		super();
		this.id = id;
		this.questionId = questionId;
		this.chosenOption = chosenOption;
		this.correctOption = correctOption;
	}

	public AnswerDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

}
