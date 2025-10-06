package com.abacus.franchise.dto;

import com.abacus.franchise.utility.ExamStatus;
import com.abacus.franchise.utility.CourseType;

import com.abacus.franchise.utility.ExamType;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class CourseDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long course_id;
	private String course_name;
	private String course_duration;
	private Integer no_of_books;
	private boolean course_status = true;
	private CourseType courseType;

	private ExamStatus Examstatus;

	private ExamType examType;

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

	public String getCourse_duration() {
		return course_duration;
	}

	public void setCourse_duration(String course_duration) {
		this.course_duration = course_duration;
	}

	public Integer getNo_of_books() {
		return no_of_books;
	}

	public void setNo_of_books(Integer no_of_books) {
		this.no_of_books = no_of_books;
	}

	public boolean isCourse_status() {
		return course_status;
	}

	public CourseType getCourseType() {
		return courseType;
	}

	public void setCourseType(CourseType courseType) {
		this.courseType = courseType;
	}

	public void setCourse_status(boolean course_status) {
		this.course_status = course_status;
	}

	public ExamType getExamType() {
		return examType;
	}

	public void setExamType(ExamType examType) {
		this.examType = examType;
	}

	public ExamStatus getExamstatus() {
		return Examstatus;
	}

	public void setExamstatus(ExamStatus examstatus) {
		Examstatus = examstatus;
	}

	public CourseDTO(Long course_id, String course_name, String course_duration, Integer no_of_books,
			boolean course_status, CourseType courseType, ExamStatus examstatus, ExamType examType) {
		super();
		this.course_id = course_id;
		this.course_name = course_name;
		this.course_duration = course_duration;
		this.no_of_books = no_of_books;
		this.course_status = course_status;
		this.courseType = courseType;
		Examstatus = examstatus;
		this.examType = examType;
	}

	public CourseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

}
