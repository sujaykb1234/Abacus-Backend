package com.abacus.franchise.dto;

public class CourseResponseDTO {

	private Long course_id;
	private String course_name;

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

	public CourseResponseDTO(Long course_id, String course_name) {
		super();
		this.course_id = course_id;
		this.course_name = course_name;
	}

	public CourseResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

}
