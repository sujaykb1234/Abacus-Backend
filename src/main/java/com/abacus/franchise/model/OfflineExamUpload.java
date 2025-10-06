package com.abacus.franchise.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class OfflineExamUpload {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long courseId;
	private String PdfImageName;
	private String pdfImageLink;
	private Date creatinTime;
	private boolean status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getPdfImageName() {
		return PdfImageName;
	}

	public void setPdfImageName(String pdfImageName) {
		PdfImageName = pdfImageName;
	}

	public String getPdfImageLink() {
		return pdfImageLink;
	}

	public void setPdfImageLink(String pdfImageLink) {
		this.pdfImageLink = pdfImageLink;
	}

	public Date getCreatinTime() {
		return creatinTime;
	}

	public void setCreatinTime(Date creatinTime) {
		this.creatinTime = creatinTime;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public OfflineExamUpload(Long id, Long courseId, String pdfImageName, String pdfImageLink, Date creatinTime,
			boolean status) {
		super();
		this.id = id;
		this.courseId = courseId;
		PdfImageName = pdfImageName;
		this.pdfImageLink = pdfImageLink;
		this.creatinTime = creatinTime;
		this.status = status;
	}

	public OfflineExamUpload() {
		super();
		// TODO Auto-generated constructor stub
	}

}
