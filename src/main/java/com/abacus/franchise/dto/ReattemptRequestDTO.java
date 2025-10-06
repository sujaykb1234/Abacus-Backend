package com.abacus.franchise.dto;

import com.abacus.franchise.utility.ExamStatus;
import com.abacus.franchise.utility.ReattemptStatus;

public class ReattemptRequestDTO {
	private Long studentId;
	private Long examId;
	private Long courseId;
	private Long franchiseId;
	private String examTime;
	private ExamStatus examStatus;
	private String examName;
	private String franchiseName;
	private String mobileNumber;
	private String studentName;
	private ReattemptStatus reattemptStatus;

	public String getExamTime() {
		return examTime;
	}

	public void setExamTime(String examTime) {
		this.examTime = examTime;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public String getStudentName() {
		return studentName;
	}

	public String getFranchiseName() {
		return franchiseName;
	}

	public void setFranchiseName(String franchiseName) {
		this.franchiseName = franchiseName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	// Getters and setters
	public Long getStudentId() {
		return studentId;
	}

	public ReattemptStatus getReattemptStatus() {
		return reattemptStatus;
	}

	public void setReattemptStatus(ReattemptStatus reattemptStatus) {
		this.reattemptStatus = reattemptStatus;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Long getExamId() {
		return examId;
	}

	public void setExamId(Long examId) {
		this.examId = examId;
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

	public ExamStatus getExamStatus() {
		return examStatus;
	}

	public void setExamStatus(ExamStatus examStatus) {
		this.examStatus = examStatus;
	}

}
