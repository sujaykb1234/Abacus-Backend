package com.abacus.franchise.dto;

import java.time.LocalDateTime;

public class EnrollmentDTO {
	private Long id;
	private Long studentId;
	private Long courseId;
	private Long franchiseId;
	private LocalDateTime enrollmentDate;
	private LocalDateTime switchDate;
	private boolean isActive;
	private String enrollmentStatus;

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

	public Long getFranchiseId() {
		return franchiseId;
	}

	public void setFranchiseId(Long franchiseId) {
		this.franchiseId = franchiseId;
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

	public String getEnrollmentStatus() {
		return enrollmentStatus;
	}

	public void setEnrollmentStatus(String enrollmentStatus) {
		this.enrollmentStatus = enrollmentStatus;
	}

	public EnrollmentDTO(Long id, Long studentId, Long courseId, Long franchiseId, LocalDateTime enrollmentDate,
			LocalDateTime switchDate, boolean isActive, String enrollmentStatus) {
		super();
		this.id = id;
		this.studentId = studentId;
		this.courseId = courseId;
		this.franchiseId = franchiseId;
		this.enrollmentDate = enrollmentDate;
		this.switchDate = switchDate;
		this.isActive = isActive;
		this.enrollmentStatus = enrollmentStatus;
	}

	public EnrollmentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

}
