package com.abacus.franchise.dto;

import com.abacus.franchise.utility.ExamStatus;
import com.abacus.franchise.utility.ExamType;
import com.abacus.franchise.utility.ReattemptStatus;

public class StudentExamDTO {

	private Long id;
	private Long studentId;
	private String studentName;
	private ExamStatus examStatus;
	private String examName;
	private String examTime;
	private Long examId;
	private Long courseId;
	private Long franchiseId;
	private ExamType examType;
	private Integer marks;

	private ReattemptStatus reattemptStatus = ReattemptStatus.PENDING;

	public ReattemptStatus getReattemptStatus() {
		return reattemptStatus;
	}

	public void setReattemptStatus(ReattemptStatus reattemptStatus) {
		this.reattemptStatus = reattemptStatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ExamType getExamType() {
		return examType;
	}

	public void setExamType(ExamType examType) {
		this.examType = examType;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public String getExamTime() {
		return examTime;
	}

	public void setExamTime(String examTime) {
		this.examTime = examTime;
	}

	public Long getExamId() {
		return examId;
	}

	public void setExamId(Long examId) {
		this.examId = examId;
	}

	public Long getFranchiseId() {
		return franchiseId;
	}

	public void setFranchiseId(Long franchiseId) {
		this.franchiseId = franchiseId;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Integer getMarks() {
		return marks;
	}

	public void setMarks(Integer marks) {
		this.marks = marks;
	}

	public ExamStatus getExamStatus() {
		return examStatus;
	}

	public void setExamStatus(ExamStatus examStatus) {
		this.examStatus = examStatus;
	}

}
