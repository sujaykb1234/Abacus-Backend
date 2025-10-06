package com.abacus.franchise.dto;

import java.util.List;

import com.abacus.franchise.model.StoredImages;
import com.abacus.franchise.utility.ExamType;

public class OfflineOnlineExamDTO {

	private Long id;
	private Long franchise_id;
	private String franchise_name;
	private String franchise_owner;


	private Long student_id;
	private ExamType examType;
	private String examName;
	private String examDate;

	private String first_name;
	private String last_name;
	private String mobile_no;
	private String email;
	private String name_on_certificate;

	private String student_profile_link;
	private String student_profile_name;

	private Long course_id;
	private Double marks;
	private List<StoredImages> pdf_images;
	private Boolean mark_as_download = false;    // all above fields for offline exam 

//	public Long getExam_id() {
//		return exam_id;
//	}
//	public void setExam_id(Long exam_id) {
//		this.exam_id = exam_id;
//	}
	
	
	public Long getStudent_id() {
		return student_id;
	}
	public String getFranchise_owner() {
		return franchise_owner;
	}
	public void setFranchise_owner(String franchise_owner) {
		this.franchise_owner = franchise_owner;
	}
	public String getExamName() {
		return examName;
	}
	public void setExamName(String examName) {
		this.examName = examName;
	}
	public String getExamDate() {
		return examDate;
	}
	public void setExamDate(String examDate) {
		this.examDate = examDate;
	}
	public void setStudent_id(Long student_id) {
		this.student_id = student_id;
	}
	public ExamType getExamType() {
		return examType;
	}
	public void setExamType(ExamType examType) {
		this.examType = examType;
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
	public Long getFranchise_id() {
		return franchise_id;
	}
	public void setFranchise_id(Long franchise_id) {
		this.franchise_id = franchise_id;
	}
	public String getFranchise_name() {
		return franchise_name;
	}
	public void setFranchise_name(String franchise_name) {
		this.franchise_name = franchise_name;
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
	public Boolean getMark_as_download() {
		return mark_as_download;
	}
	public void setMark_as_download(Boolean mark_as_download) {
		this.mark_as_download = mark_as_download;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	

}
