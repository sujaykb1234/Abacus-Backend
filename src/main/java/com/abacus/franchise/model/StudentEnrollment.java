package com.abacus.franchise.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.persistence.Id;

@Entity
public class StudentEnrollment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "student_id", nullable = false)
	private Student student;

	@ManyToOne
//	@JoinColumn(name = "course_id", nullable = false)
	private Course course;

	@ManyToOne
	@JoinColumn(name = "franchise_id", nullable = false)
	private Franchise franchise;

	private LocalDateTime enrollmentDate;
	private LocalDateTime switchDate;
	private boolean isActive;
	private String enrollmentStatus; // ACTIVE /DEACTIVATE

	public Long getId() {
		return id;
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

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Franchise getFranchise() {
		return franchise;
	}

	public String getEnrollmentStatus() {
		return enrollmentStatus;
	}

	public void setEnrollmentStatus(String enrollmentStatus) {
		this.enrollmentStatus = enrollmentStatus;
	}

	public void setFranchise(Franchise franchise) {
		this.franchise = franchise;
	}

	public LocalDateTime getEnrollmentDate() {
		return enrollmentDate;
	}

	public void setEnrollmentDate(LocalDateTime enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}

	public LocalDateTime getSwitchDate() {
		return switchDate;
	}

	public void setSwitchDate(LocalDateTime switchDate) {
		this.switchDate = switchDate;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public StudentEnrollment(Long id, Student student, Course course, Franchise franchise, LocalDateTime enrollmentDate,
			LocalDateTime switchDate, boolean isActive) {
		super();
		this.id = id;
		this.student = student;
		this.course = course;
		this.franchise = franchise;
		this.enrollmentDate = enrollmentDate;
		this.switchDate = switchDate;
		this.isActive = isActive;
	}

	public StudentEnrollment() {
		super();
		// TODO Auto-generated constructor stub
	}

}