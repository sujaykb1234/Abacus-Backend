package com.abacus.franchise.model;

import com.abacus.franchise.utility.CourseType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long course_id;
	private String course_name;
	private String course_duration;
	private Integer no_of_books;
	private CourseType courseType;  //ABACUS,OTHER
	private boolean course_status = true;

	public Long getCourse_id() {
		return course_id;
	}

	public void setCourse_id(Long course_id) {
		this.course_id = course_id;
	}

	public String getCourse_name() {
		return course_name;
	}

	public CourseType getCourseType() {
		return courseType;
	}

	public void setCourseType(CourseType courseType) {
		this.courseType = courseType;
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

	public void setCourse_status(boolean course_status) {
		this.course_status = course_status;
	}

	public Course(Long course_id, String course_name, String course_duration, Integer no_of_books,
			CourseType courseType, boolean course_status) {
		super();
		this.course_id = course_id;
		this.course_name = course_name;
		this.course_duration = course_duration;
		this.no_of_books = no_of_books;
		this.courseType = courseType;
		this.course_status = course_status;
	}

	public Course() {
		super();
	}

}
