package com.abacus.franchise.model;

import com.abacus.franchise.utility.CourseStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class StudentCourseDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long studentId;
	private CourseStatus courseStatus; // in-progress/completed
	private Long courseId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public CourseStatus getCourseStatus() {
		return courseStatus;
	}

	public void setCourseStatus(CourseStatus courseStatus) {
		this.courseStatus = courseStatus;
	}

	public StudentCourseDetails(Long id, Long studentId, CourseStatus courseStatus, Long courseId) {
		super();
		this.id = id;
		this.studentId = studentId;
		this.courseStatus = courseStatus;
		this.courseId = courseId;
	}

	public StudentCourseDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

}
