package com.abacus.franchise.dto;

import com.abacus.franchise.model.Student;
import com.abacus.franchise.utility.ExamType;
import com.abacus.franchise.utility.ReattemptStatus;

public class StudentExamResponseDTO {

	private Long id;

	private Boolean mark_as_dawnload;
	private Long examId;
	private Long courseId;
	private Long franchiseId;
	private ExamType examType;
	private Student student;
	private String examStatus;
	private String examName;

	private ReattemptStatus reattemptStatus = ReattemptStatus.PENDING;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getExamStatus() {
		return examStatus;
	}

	public void setExamStatus(String examStatus) {
		this.examStatus = examStatus;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}


	public ExamType getExamType() {
		return examType;
	}

	public void setExamType(ExamType examType) {
		this.examType = examType;
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

	public ReattemptStatus getReattemptStatus() {
		return reattemptStatus;
	}

	public void setReattemptStatus(ReattemptStatus reattemptStatus) {
		this.reattemptStatus = reattemptStatus;
	}

	public Boolean getMark_as_dawnload() {
		return mark_as_dawnload;
	}

	public void setMark_as_dawnload(Boolean mark_as_dawnload) {
		this.mark_as_dawnload = mark_as_dawnload;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	

}
