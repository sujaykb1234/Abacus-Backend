package com.abacus.franchise.dto;

import java.util.List;

import com.abacus.franchise.model.StoredImages;
import com.abacus.franchise.utility.ExamType;
import com.abacus.franchise.utility.ReattemptStatus;

import jakarta.validation.constraints.NotNull;

public class OfflineExamDTO {

	private Long offline_exam_id;

	@NotNull(message = "Options must not be null")
	private Long exam_id;

	@NotNull(message = "Options must not be null")
	private Long student_id;

	@NotNull(message = "Exam Type must not be null")
	private ExamType examType;

	private String first_name;
	private String last_name;
	private String mobile_no;
	private String email;
	private String name_on_certificate;

	private String student_profile_link;
	private String student_profile_name;
	

	@NotNull(message = "Options must not be null")
	private Long franchise_id;

	private String franchise_name;

	@NotNull(message = "Options must not be null")
	private Long course_id;

	@NotNull(message = "Options must not be null")
	private Double marks;

	
	private Boolean mark_as_download = false;

	private List<StoredImages> pdf_images;
	
	private ReattemptStatus reattemptStatus;


	private String creation_time;
	private Boolean status = true;

	public Long getOffline_exam_id() {
		return offline_exam_id;
	}

	public void setOffline_exam_id(Long offline_exam_id) {
		this.offline_exam_id = offline_exam_id;
	}

	public Long getExam_id() {
		return exam_id;
	}

	public void setExam_id(Long exam_id) {
		this.exam_id = exam_id;
	}

	public Long getStudent_id() {
		return student_id;
	}

	public void setStudent_id(Long student_id) {
		this.student_id = student_id;
	}

	public Long getFranchise_id() {
		return franchise_id;
	}

	public void setFranchise_id(Long franchise_id) {
		this.franchise_id = franchise_id;
	}

	public Long getCourse_id() {
		return course_id;
	}

	public void setCourse_id(Long course_id) {
		this.course_id = course_id;
	}

	public Double getMarks() {
		return marks;
	}

	public void setMarks(Double marks) {
		this.marks = marks;
	}

	public List<StoredImages> getPdf_images() {
		return pdf_images;
	}

	public void setPdf_images(List<StoredImages> pdf_images) {
		this.pdf_images = pdf_images;
	}

	public String getCreation_time() {
		return creation_time;
	}

	public void setCreation_time(String creation_time) {
		this.creation_time = creation_time;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getStudent_profile_link() {
		return student_profile_link;
	}

	public void setStudent_profile_link(String student_profile_link) {
		this.student_profile_link = student_profile_link;
	}

	public String getStudent_profile_name() {
		return student_profile_name;
	}

	public void setStudent_profile_name(String student_profile_name) {
		this.student_profile_name = student_profile_name;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getMobile_no() {
		return mobile_no;
	}

	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName_on_certificate() {
		return name_on_certificate;
	}

	public void setName_on_certificate(String name_on_certificate) {
		this.name_on_certificate = name_on_certificate;
	}

	public ExamType getExamType() {
		return examType;
	}

	public void setExamType(ExamType examType) {
		this.examType = examType;
	}

	public String getFranchise_name() {
		return franchise_name;
	}

	public void setFranchise_name(String franchise_name) {
		this.franchise_name = franchise_name;
	}

	public Boolean getMark_as_download() {
		return mark_as_download;
	}

	public void setMark_as_download(Boolean mark_as_download) {
		this.mark_as_download = mark_as_download;
	}
	
	

	public ReattemptStatus getReattemptStatus() {
		return reattemptStatus;
	}

	public void setReattemptStatus(ReattemptStatus reattemptStatus) {
		this.reattemptStatus = reattemptStatus;
	}

	public OfflineExamDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

}