package com.abacus.franchise.dto;

public class CertificateDTO {

	private Long studentId;
	private Long courseId;
	private Long examId;
	private boolean isGenerated = false;

	private Boolean status = true;

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Long getCourseId() {
		return courseId;
	}

	public boolean isGenerated() {
		return isGenerated;
	}

	public void setGenerated(boolean isGenerated) {
		this.isGenerated = isGenerated;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Boolean getStatus() {
		return status;
	}

	public Long getExamId() {
		return examId;
	}

	public void setExamId(Long examId) {
		this.examId = examId;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public CertificateDTO(Long studentId, Long courseId, Long examId, boolean isGenerated, Boolean status) {
		super();
		this.studentId = studentId;
		this.courseId = courseId;
		this.examId = examId;
		this.isGenerated = isGenerated;
		this.status = status;
	}

	public CertificateDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

}
