package com.abacus.franchise.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class FranchiseKitRequestsLogs {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long franchise_id;
	private String franchise_name;
	private String franchise_number;
	private Long courseId;
	private String courseName;
	private Integer orderedKits;
	private String franchiseMobile;
	private String requestedDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getFranchise_number() {
		return franchise_number;
	}

	public void setFranchise_number(String franchise_number) {
		this.franchise_number = franchise_number;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Integer getOrderedKits() {
		return orderedKits;
	}

	public void setOrderedKits(Integer orderedKits) {
		this.orderedKits = orderedKits;
	}

	public String getFranchiseMobile() {
		return franchiseMobile;
	}

	public void setFranchiseMobile(String franchiseMobile) {
		this.franchiseMobile = franchiseMobile;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getRequestedDate() {
		return requestedDate;
	}

	public void setRequestedDate(String requestedDate) {
		this.requestedDate = requestedDate;
	}

	public FranchiseKitRequestsLogs(Long id, Long franchise_id, String franchise_name, String franchise_number,
			String courseName, Integer orderedKits, String franchiseMobile, Long courseId, String requestedDate) {
		super();
		this.id = id;
		this.franchise_id = franchise_id;
		this.franchise_name = franchise_name;
		this.franchise_number = franchise_number;
		this.courseName = courseName;
		this.orderedKits = orderedKits;
		this.franchiseMobile = franchiseMobile;
		this.courseId = courseId;
		this.requestedDate = requestedDate;
	}

	public FranchiseKitRequestsLogs() {
		super();
	}

}
