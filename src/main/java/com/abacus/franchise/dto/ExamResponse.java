package com.abacus.franchise.dto;

public class ExamResponse {

	private Long id;
	private String questionName;
	private String questionImageLink;
	private String options;
	private String correctOptions;
	private Long studentId;
	private String courseId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestionName() {
		return questionName;
	}

	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

	public String getQuestionImageLink() {
		return questionImageLink;
	}

	public void setQuestionImageLink(String questionImageLink) {
		this.questionImageLink = questionImageLink;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public String getCorrectOptions() {
		return correctOptions;
	}

	public void setCorrectOptions(String correctOptions) {
		this.correctOptions = correctOptions;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public ExamResponse(Long id, String questionName, String questionImageLink, String options, String correctOptions,
			Long studentId, String courseId) {
		super();
		this.id = id;
		this.questionName = questionName;
		this.questionImageLink = questionImageLink;
		this.options = options;
		this.correctOptions = correctOptions;
		this.studentId = studentId;
		this.courseId = courseId;
	}

	public ExamResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

}
