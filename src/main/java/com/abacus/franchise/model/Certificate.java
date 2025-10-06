package com.abacus.franchise.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Certificate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long certificate_id;
	private String certificate_number;
	private Integer studentMarks;
	private String examName;
	private String student_name;
	private Long studentId;
	private String course_name;
	private String admission_date;
	private Long courseId;
	private String completion_date;
	private String studentProfImgLink;
	private boolean isGenerated = false;

	private Boolean status = true;

	public Long getCertificate_id() {
		return certificate_id;
	}

	public void setCertificate_id(Long certificate_id) {
		this.certificate_id = certificate_id;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getCertificate_number() {
		return certificate_number;
	}

	public void setCertificate_number(String certificate_number) {
		this.certificate_number = certificate_number;
	}

	public Integer getStudentMarks() {
		return studentMarks;
	}

	public void setStudentMarks(Integer studentMarks) {
		this.studentMarks = studentMarks;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public String getStudent_name() {
		return student_name;
	}

	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}

	public String getCourse_name() {
		return course_name;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	public String getAdmission_date() {
		return admission_date;
	}

	public void setAdmission_date(String admission_date) {
		this.admission_date = admission_date;
	}

	public String getCompletion_date() {
		return completion_date;
	}

	public void setCompletion_date(String completion_date) {
		this.completion_date = completion_date;
	}

	public String getStudentProfImgLink() {
		return studentProfImgLink;
	}

	public void setStudentProfImgLink(String studentProfImgLink) {
		this.studentProfImgLink = studentProfImgLink;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public boolean isGenerated() {
		return isGenerated;
	}

	public void setGenerated(boolean isGenerated) {
		this.isGenerated = isGenerated;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Certificate(Long certificate_id, String certificate_number, Integer studentMarks, String examName,
			String student_name, Long studentId, String course_name, String admission_date, Long courseId,
			String completion_date, String studentProfImgLink, boolean isGenerated, Boolean status) {
		super();
		this.certificate_id = certificate_id;
		this.certificate_number = certificate_number;
		this.studentMarks = studentMarks;
		this.examName = examName;
		this.student_name = student_name;
		this.studentId = studentId;
		this.course_name = course_name;
		this.admission_date = admission_date;
		this.courseId = courseId;
		this.completion_date = completion_date;
		this.studentProfImgLink = studentProfImgLink;
		this.isGenerated = isGenerated;
		this.status = status;
	}

	public Certificate() {
		super();
	}

}
