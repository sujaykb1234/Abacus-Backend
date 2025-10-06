package com.abacus.franchise.model;

import java.util.List;

import com.abacus.franchise.utility.ExamType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class OfflineExam {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long offline_exam_id;
	
	private Long exam_id;
	
	private ExamType examType; 
	private Long student_id;
	private Long franchise_id;
	private Long course_id;
	private Double marks;

	@ManyToMany(cascade = CascadeType.ALL)
	private List<StoredImages> pdf_images;
	
	private Boolean mark_as_download=false;

	private String creation_time;
	private Boolean status=true;


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

	public List<StoredImages> getPdf_images() {
		return pdf_images;
	}

	public void setPdf_images(List<StoredImages> pdf_images) {
		this.pdf_images = pdf_images;
	}

	public ExamType getExamType() {
		return examType;
	}

	public void setExamType(ExamType examType) {
		this.examType = examType;
	}

	public Boolean getMark_as_download() {
		return mark_as_download;
	}

	public void setMark_as_download(Boolean mark_as_download) {
		this.mark_as_download = mark_as_download;
	}



}